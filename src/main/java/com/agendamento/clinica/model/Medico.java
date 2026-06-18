package com.agendamento.clinica.model;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//classe que cria a entidade medico, com todos os atributos obrigatorios para cadastro do medico no banco de dados
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    String nome;
    String especialidade;
    String crm;



    public Medico(){}

    public Medico(Long id, String nome, String especialidade, String crm) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }   
    public void setId(Long id) {
        this.id = id;
    }
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

}
