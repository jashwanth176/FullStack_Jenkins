package com.klef.dev.service.impl;

import com.klef.dev.entity.Charity;
import com.klef.dev.repository.CharityRepository;
import com.klef.dev.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharityServiceImpl implements CharityService {

    @Autowired
    private CharityRepository charityRepository;

    @Override
    public Charity create(Charity charity) {
        charity.setCollectedAmount(charity.getCollectedAmount() == null ? 0.0 : charity.getCollectedAmount());
        return charityRepository.save(charity);
    }

    @Override
    public Charity update(Charity charity) {
        return charityRepository.save(charity);
    }

    @Override
    public void delete(Long id) {
        charityRepository.deleteById(id);
    }

    @Override
    public Charity getById(Long id) {
        return charityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Charity> getAll() {
        return charityRepository.findAll();
    }
}
