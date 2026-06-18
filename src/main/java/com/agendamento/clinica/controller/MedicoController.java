package com.agendamento.clinica.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.clinica.dto.CadastrasMedicoRequest;
import com.agendamento.clinica.service.MedicoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarMedico(@RequestBody CadastrasMedicoRequest request){
        try{
            medicoService.cadastrarMedico(
            request.getNome(),
            request.getEspecialidade(),
            request.getCrm());
            
            return ResponseEntity.status(201).body("Médico cadastrado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    

    }
    
}
    
