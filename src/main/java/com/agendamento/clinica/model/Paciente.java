package com.agendamento.clinica.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;


//classe que cria a entidade paciente, com todos os atributos obrigatorios para o cadastro do paciente no banco de dados
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    public Paciente(){}

    public Paciente(Long id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
