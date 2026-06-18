package com.agendamento.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.clinica.model.Paciente;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
//metodo para identificar se o cpf ja esta cadastrado
	@Query("SELECT p.id FROM Paciente p WHERE p.cpf = :cpf")
    boolean existsByCpf(String cpf);

    //identificando paciente por nome, para saber os dados dele, caso alguem esqueça
    List<Paciente> findByNomeContainingIgnoreCase(String pacienteNome);
}
