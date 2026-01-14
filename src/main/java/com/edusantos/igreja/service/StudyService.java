package com.edusantos.igreja.service;

import com.edusantos.igreja.entities.Study;
import com.edusantos.igreja.repository.StudyRepository;
import com.edusantos.igreja.service.execeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public List<Study> findAll(){
        return studyRepository.findAll();
    }

    public Study findById(Long id){
        return studyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Transactional
    public Study insert( Study study){
        return studyRepository.save(study);
    }
    @Transactional
    public Study update(Long id, Study study){
        Study existingStudy = studyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        existingStudy.setTheDate(study.getTheDate());
        existingStudy.setSubject(study.getSubject());
        existingStudy.setDescription(study.getDescription());
        existingStudy.setNotes(study.getNotes());

        return studyRepository.save(existingStudy);

    }

    @Transactional
    public void delete(Long id){
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        studyRepository.delete(study);
    }
}
