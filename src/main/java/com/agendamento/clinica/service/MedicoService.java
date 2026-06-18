package com.agendamento.clinica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agendamento.clinica.model.Medico;
import com.agendamento.clinica.repository.MedicoRepository;


@Service
public class MedicoService {
    //Injetando dependencias do repositorio.
    private final MedicoRepository medicoRepository;
    
    //construtor para realizar a injeção das dependencias
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    //metodo para cadastrar medico e validar campos obrigatorios.
    public void cadastrarMedico(String nome, String especialidade, String crm) {
        //se nao tiver nome, disparar exceção
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("O nome do médico é obrigatório e não pode ser vazio");
        }
        // se nao tiver crm, disparar exceção
        if (crm == null || crm.trim().isEmpty()) {
            throw new RuntimeException("O CRM é obrigatório e não pode ser vazio");
        }
        //se nao tiver especialidade, disparar exceção
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new RuntimeException("A especialidade do médico é obrigatória e não pode ser vazia");
        }
        //se o crm ja estiver cadastrado, disparar exceção
        if (medicoRepository.existsByCrm(crm)) {
            throw new RuntimeException("Já existe um médico cadastrado com esse CRM");
        }
        //salvando medico no banco de dados
        Medico medico = new Medico();
        medico.setNome(nome);
        medico.setEspecialidade(especialidade);
        medico.setCrm(crm);
        
        medicoRepository.save(medico);
    }

    //Listando dados do medico por nome, para caso alguem esqueça o id do medico, possa buscar por nome e saber os dados dele
    public List<Medico> listarPorNomeMedico(String medicoNome){
        return medicoRepository.findByNomeContainingIgnoreCase(medicoNome);
    }
}
