package com.edusantos.igreja.service;

import com.edusantos.igreja.entities.Church;
import com.edusantos.igreja.entities.Member;
import com.edusantos.igreja.repository.ChurchRepository;
import com.edusantos.igreja.service.execeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChurchService {

    //Injeção de dependência do repositório ChurchRepository.
    //O Spring injeta automaticamente a implementação.

    private final ChurchRepository churchRepository;

    public ChurchService(ChurchRepository churchRepository) {
        this.churchRepository = churchRepository;
    }

    //Busca todas as igrejas cadastradas no banco de dados.
    public List<Church> findAll(){
        return churchRepository.findAll();
    }

    //Busca uma igreja pelo seu ID.
    // Caso não encontre, lança uma exceção ResourceNotFoundException.
    public Church finById(Long id){
        return churchRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    //Retorna todos os membros associados a uma igreja específica.
    public List<Member> findAllMemberByChurchId(Long churchId){
        Church church = churchRepository.findById(churchId).orElseThrow(() -> new ResourceNotFoundException(churchId));
        return church.getMembers();
    }

    //Remove uma igreja do banco de dados.
    //Antes de deletar, desvincula todos os membros da igreja
    //para evitar problemas de integridade referencial.
    @Transactional
    public void delete(Long id){
        Church church = churchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        //Desviculando o membro
        church.getMembers().forEach(member -> member.setChurch(null));
        churchRepository.delete(church);
    }

    //Insere uma nova igreja no banco de dados
    @Transactional
    public Church insert(Church church){
        return churchRepository.save(church);
    }

    //Atualiza os dados de uma igreja existente.
    //Primeiro busca a igreja pelo ID e depois atualiza campo por campo.
    @Transactional
    public Church update(Long id, Church church){
        Church existingChurch = churchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        //Atualiza cada campo da entidade
        existingChurch.setName(church.getName());
        existingChurch.setResponsible(church.getResponsible());
        existingChurch.setWebsite(church.getWebsite());
        existingChurch.setType(church.getType());
        existingChurch.setAddress(church.getAddress());
        existingChurch.setCity(church.getCity());
        existingChurch.setState(church.getState());
        existingChurch.setFoundationdate(church.getFoundationdate());
        existingChurch.setCnpj(church.getCnpj());
        existingChurch.setPhone(church.getPhone());

        //salvando as alterações no banco
        return churchRepository.save(existingChurch);
    }
}
