package com.klef.dev.service;

import com.klef.dev.entity.Donation;
import java.util.List;

public interface DonationService {
    Donation donate(Long charityId, Donation donation);
    List<Donation> listByCharity(Long charityId);
}
