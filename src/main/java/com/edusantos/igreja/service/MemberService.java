package com.edusantos.igreja.service;

import com.edusantos.igreja.entities.Church;
import com.edusantos.igreja.entities.Member;
import com.edusantos.igreja.repository.ChurchRepository;
import com.edusantos.igreja.repository.MemberRepository;
import com.edusantos.igreja.service.execeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final ChurchRepository churchRepository;

    public MemberService(MemberRepository memberRepository, ChurchRepository churchRepository) {
        this.memberRepository = memberRepository;
        this.churchRepository = churchRepository;
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long id){
        Optional<Member> objetc = memberRepository.findById(id);
        return objetc.get();
    }

    @Transactional
    public Member createMember(Long churchId, Member member){
        try{
            Church churchEntity = churchRepository.findById(churchId).get();

            member.setChurch(churchEntity);

            return memberRepository.save(member);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(churchId);
        }
    }

    @Transactional
    public Member updateMember(Long id, Long churchId, Member object){
        try {
            Church churchEntity = churchRepository.findById(churchId).get();

            Member entity = memberRepository.getReferenceById(id);

            entity.setChurch(churchEntity);

            entity.setStatus(object.getStatus());
            entity.setRole(object.getRole());
            entity.setBaptismDate(object.getBaptismDate());
            entity.setAdmission(object.getAdmission());
            entity.setName(object.getName());
            entity.setGender(object.getGender());
            entity.setBirthdate(object.getBirthdate());
            entity.setAddress(object.getAddress());
            entity.setState(object.getState());
            entity.setOccupation(object.getOccupation());

            return memberRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id){
        try{
            memberRepository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
}
