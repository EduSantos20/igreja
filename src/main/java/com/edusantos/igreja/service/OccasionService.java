package com.edusantos.igreja.service;

import com.edusantos.igreja.entities.Occasion;
import com.edusantos.igreja.repository.OccasionRepository;
import com.edusantos.igreja.service.execeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccasionService {

    private final OccasionRepository occasionRepository;

    public OccasionService(OccasionRepository occasionRepository) {
        this.occasionRepository = occasionRepository;
    }

    public List<Occasion> findAll(){
        return occasionRepository.findAll();
    }

    public Occasion findById(Long id){
        return occasionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public Occasion insert(Occasion occasion){
        return occasionRepository.save(occasion);
    }

    @Transactional
    public Occasion update(Long id, Occasion occasion){
        Occasion existingOccasion = occasionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));

        existingOccasion.setName(occasion.getName());
        existingOccasion.setDescription(occasion.getDescription());
        existingOccasion.setStart(occasion.getStart());
        existingOccasion.setExist(occasion.getExist());

        return occasionRepository.save(existingOccasion);
    }

    @Transactional
    public void delete(Long id){
        Occasion occasion = occasionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        occasionRepository.delete(occasion);
    }
}
