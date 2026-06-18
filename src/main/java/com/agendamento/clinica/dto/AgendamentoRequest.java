package com.agendamento.clinica.dto;

import java.time.LocalDateTime;


//Classe que cria o dto, que é a classe de transferência de dados, para receber os dados do agendamento da consulta, e passar para o service, para ser processado e salvo no banco de dados
public class AgendamentoRequest {
    private Long medicoId;
    private Long pacienteId;
    private LocalDateTime dataDesejada;

    public AgendamentoRequest(){}

    public AgendamentoRequest(Long medicoId, Long pacienteId, LocalDateTime dataDesejada) {
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.dataDesejada = dataDesejada;
    }

    // Getters e Setters
    public Long getMedicoId() {
        return medicoId;
    }
    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }
    public Long getPacienteId() {
        return pacienteId;
    }
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
    public LocalDateTime getDataDesejada() {
        return dataDesejada;
    }
    public void setDataDesejada(LocalDateTime dataDesejada) {
        this.dataDesejada = dataDesejada;
    }
}