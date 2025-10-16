package com.klef.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.dev.entity.Donation;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByCharityId(Long charityId);
}
