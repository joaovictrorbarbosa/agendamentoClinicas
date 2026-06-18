package com.agendamento.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.clinica.model.Medico;

public interface  MedicoRepository extends JpaRepository<com.agendamento.clinica.model.Medico, Long> {
    //metodo para identificar se o crm ja esta cadastrado
    boolean existsByCrm(String crm);
    
    //identificando medico por nome, para saber os dados dele, caso alguem esqueça
    List<Medico> findByNomeContainingIgnoreCase(String medicoNome);
}