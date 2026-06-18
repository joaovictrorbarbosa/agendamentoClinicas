package com.agendamento.clinica.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agendamento.clinica.model.Consulta;
import com.agendamento.clinica.repository.ConsultaRepository;
import com.agendamento.clinica.repository.MedicoRepository;
import com.agendamento.clinica.repository.PacienteRepository;
@Service
public class ConsultaService {
    //Injeção de dependencias dos repositorios, para acessar dados em Db e incluir as logicas de negocio.
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    //construtor para realizar a injeção das dependencias
    public ConsultaService(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    //Criação do método de agendamento das consultas
    public void agendarConsulta(Long medicoId, Long pacienteId, LocalDateTime dataDesejada) {
        //colocando variaveis para busca de medico e paciente em banco de dados, caso nao, dispara exceção
        var medico = medicoRepository.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        var paciente = pacienteRepository.findById(pacienteId).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        
        //limite mminimo de 2 dias para oo agendamento da consulta, caso nao, dispara exceção
         LocalDateTime limiteMinimo = LocalDateTime.now().plusDays(2); 

         if (dataDesejada.isBefore(limiteMinimo)){
            throw new RuntimeException("A consulta deve ser agendada com pelo menos 2 dias de antecedência");
         }         
         //se a data desejada for fora de dia de semana, dispara exceção
         if (dataDesejada.getDayOfWeek().equals(DayOfWeek.SATURDAY) || dataDesejada.getDayOfWeek().equals(DayOfWeek.SUNDAY)|| (dataDesejada.getHour() < 7 || dataDesejada.getHour() >= 18)) {
            throw new RuntimeException("A consulta deve ser agendada durante o horário comercial (7h às 18h) e em dias úteis");
         }
         //se o medico ja tiver consulta agendada no dia e horario, disparar exceção
         if (consultaRepository.existsByMedicoAndDataHora(medico, dataDesejada)) {
            throw new RuntimeException("O médico já possui uma consulta agendada para esse horário");
         }
         
         //salvar a nova consulta em banco de dados.
        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setDataHora(dataDesejada);

        consultaRepository.save(novaConsulta);
    }

//Listar todas as consultas
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    //Mostrar todas as consultas agendadas para uma data definida
    public List<Consulta> listarPorDia(LocalDate data) {
        LocalDateTime inicioDoDia = data.atStartOfDay(); 
        LocalDateTime fimDoDia = data.atTime(LocalTime.MAX);
        
        return consultaRepository.findByDataHoraBetween(inicioDoDia, fimDoDia);
    }



    //Listando consultas por medico
    public List<Consulta> listarPorMedicoId(Long medicoId){
        //Primeiro verificando se o medico esta cadastrado
        if(!medicoRepository.existsById(medicoId)){
            throw new RuntimeException("Médico não encontrado");
        }

        //se estiver ok, listar as consultas daquele medico
        return consultaRepository.findByMedicoId(medicoId);
    }

    //Listando consultas por paciente
    public List<Consulta> listarPorPaciente(Long pacienteId){
        //Primeiro verificando se o paciente esta cadastrado
        if(!pacienteRepository.existsById(pacienteId)){
            throw new RuntimeException("Paciente não encontrado");
        }

        //se estiver ok, listar as consultas daquele paciente
        return consultaRepository.findByPacienteId(pacienteId);
    }
}
