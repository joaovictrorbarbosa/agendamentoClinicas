package com.agendamento.clinica.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Classe que cria a entidade de consulta, com todos os atributos obrigatorios para o cadastro da consulta, no banco de dados
@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Consulta(){}

    public Consulta(Long id, LocalDateTime dataHora, Medico medico, Paciente paciente) {
        this.id = id;
        this.dataHora = dataHora;
        this.medico = medico;
        this.paciente = paciente;
    }

    // Getters e Setters
    public Long getId() {       
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
      
}
