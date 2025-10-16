package com.klef.dev.service.impl;

import com.klef.dev.entity.Charity;
import com.klef.dev.entity.Donation;
import com.klef.dev.repository.CharityRepository;
import com.klef.dev.repository.DonationRepository;
import com.klef.dev.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CharityRepository charityRepository;

    @Override
    public Donation donate(Long charityId, Donation donation) {
        Charity charity = charityRepository.findById(charityId).orElseThrow(() -> new IllegalArgumentException("Charity not found"));
        donation.setCharity(charity);
        Donation saved = donationRepository.save(donation);
        charity.setCollectedAmount(charity.getCollectedAmount() + donation.getAmount());
        charityRepository.save(charity);
        return saved;
    }

    @Override
    public List<Donation> listByCharity(Long charityId) {
        return donationRepository.findByCharityId(charityId);
    }
}
