package com.agendamento.clinica.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.clinica.dto.AgendamentoRequest;
import com.agendamento.clinica.model.Consulta;
import com.agendamento.clinica.model.Medico;
import com.agendamento.clinica.model.Paciente;
import com.agendamento.clinica.service.ConsultaService;
import com.agendamento.clinica.service.MedicoService;
import com.agendamento.clinica.service.PacienteService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public ConsultaController(ConsultaService consultaService, MedicoService medicoService, PacienteService pacienteService) {
        this.consultaService = consultaService;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping()
   public ResponseEntity<String> agendar(@RequestBody AgendamentoRequest request) {
        try {
            consultaService.agendarConsulta(
            request.getMedicoId(), 
            request.getPacienteId(), 
            request.getDataDesejada());
            
            return ResponseEntity.ok("Consulta agendada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/porMedico/{medicoId}")
    public ResponseEntity<List<Consulta>> listarConsultasPorMedico(@PathVariable Long medicoId){
        try {
            return ResponseEntity.ok(consultaService.listarPorMedicoId(medicoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/porPaciente/{pacienteId}")
    public ResponseEntity<List<Consulta>> listarConsultasPorPaciente(@PathVariable Long pacienteId){
        try {
            return ResponseEntity.ok(consultaService.listarPorPaciente(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/porData/{data}")
    public ResponseEntity<List<Consulta>> listarConsultasPorData(@PathVariable LocalDate data){
        try {
            return ResponseEntity.ok(consultaService.listarPorDia(data));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/porMedicoNome/{medicoNome}")
    public ResponseEntity<List<Medico>> listarDadosPorMedicoNome(@PathVariable String medicoNome){
        try {
            return ResponseEntity.ok(medicoService.listarPorNomeMedico(medicoNome));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/porPacienteNome/{pacienteNome}")
    public ResponseEntity<List<Paciente>> listarDadosPacienteNome(@PathVariable String pacienteNome){
        try {
            return ResponseEntity.ok(pacienteService.listarPorNomePaciente(pacienteNome));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Consulta>> listarTodasConsultas(){
        try {
            return ResponseEntity.ok(consultaService.listarTodas());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}