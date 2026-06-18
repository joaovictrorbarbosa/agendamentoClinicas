package com.agendamento.clinica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.clinica.dto.CadastroPacienteRequest;
import com.agendamento.clinica.service.PacienteService;

//classe que cria o controller, onde realizaremos as requisições da api e repassa para a service.
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarPaciente(@RequestBody CadastroPacienteRequest request){
        try{
            pacienteService.cadastrarPaciente(
                request.getNome(),
                request.getCpf(),
                request.getEmail()
            ); 
            return ResponseEntity.status(201).body("Paciente cadastrado com sucesso");  
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
