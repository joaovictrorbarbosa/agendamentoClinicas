package com.agendamento.clinica.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.clinica.model.Consulta;
import com.agendamento.clinica.model.Medico;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    //etodo para identificar se o medico ja possui consulta agendada no dia e horario 
    boolean existsByMedicoAndDataHora(Medico medico, LocalDateTime data);

    //Metodo para identificar as consultas em um periodo de tempo determinado
    List<Consulta> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);

    //identificando consultas que um medico tem
    List<Consulta> findByMedicoId(Long medicoId);

    //identificando consultas que um paciente marcou
    List<Consulta> findByPacienteId(Long pacienteId);

    

    

}
