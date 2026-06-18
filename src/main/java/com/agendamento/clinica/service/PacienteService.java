package com.agendamento.clinica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agendamento.clinica.model.Paciente;
import com.agendamento.clinica.repository.PacienteRepository;

@Service
public class PacienteService {
    //Injentando depedencias do repositorio
    private final PacienteRepository pacienteRepository;

    //construtor para injetar as depedencias
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    //criação do metodo de cadastro de paciente, validando campos obrigatorios
public void cadastrarPaciente(String nome, String cpf, String email){
    //Se nao tiver nome, disparar exceção
    if (nome == null || nome.trim().isEmpty()){
        throw new RuntimeException("O nome do paciente é obrigatório e não pode ser vazio");
    }
    //se nao tiver cpf, disparar exceção
    if (cpf == null || cpf.trim().isEmpty()){
        throw new RuntimeException("O CPF é obrigatório e não pode ser vazio");
    }
    //se nao tiver email, disparar exceção
    if (email == null || email.trim().isEmpty()){
        throw new RuntimeException("O email é obrigatório e não pode ser vazio");
    }
    //se o paciente ja tiver aquele cpf cadastrado, disparar exceção
    if (pacienteRepository.existsByCpf(cpf)){
        throw new RuntimeException("Já existe um paciente cadastrado com esse CPF");
    }

    // salvando
    Paciente paciente = new Paciente();
    paciente.setNome(nome);
    paciente.setCpf(cpf);
    paciente.setEmail(email);
    pacienteRepository.save(paciente);
    }


    //Listando dados do paciente por nome, para caso alguem esqueça o id do paciente, possa buscar por nome e saber os dados dele
    public List<Paciente> listarPorNomePaciente(String pacienteNome){
        return pacienteRepository.findByNomeContainingIgnoreCase(pacienteNome);
    }
}
