package com.edusantos.igreja.service;

import com.edusantos.igreja.entities.Financial;
import com.edusantos.igreja.repository.FinancialRepository;
import com.edusantos.igreja.service.execeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialService {

    private final FinancialRepository financialRepository;

    public FinancialService(FinancialRepository financialRepository) {
        this.financialRepository = financialRepository;
    }

    public List<Financial> findAll(){
        return financialRepository.findAll();
    }

    public Financial findById(Long id){
        return financialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public Financial insert(Financial financial){
        return financialRepository.save(financial);
    }

    @Transactional
    public Financial update(Long id, Financial financial){
        Financial existingFinancial = financialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        existingFinancial.setType(financial.getType());
        existingFinancial.setAmount(financial.getAmount());
        existingFinancial.setTheDate(financial.getTheDate());
        existingFinancial.setPaymentMethod(financial.getPaymentMethod());
        existingFinancial.setDescription(financial.getDescription());

        return financialRepository.save(existingFinancial);
    }

    @Transactional
    public void delete(Long id){
        Financial financial = financialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        financialRepository.delete(financial);
    }
}
