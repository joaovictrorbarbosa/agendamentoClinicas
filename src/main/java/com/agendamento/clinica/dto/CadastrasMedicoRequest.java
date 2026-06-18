package com.agendamento.clinica.dto;


//Classe que cria o dto, que é a classe de transferência de dados, para receber os dados do agendamento da consulta, e passar para o service, para ser processado e salvo no banco de dados//
public class CadastrasMedicoRequest {
    private Long id;
    private String nome;
    private String especialidade;
    private String crm;


    public CadastrasMedicoRequest(){}

    public CadastrasMedicoRequest(Long id, String nome, String especialidade, String crm) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.id = id;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}