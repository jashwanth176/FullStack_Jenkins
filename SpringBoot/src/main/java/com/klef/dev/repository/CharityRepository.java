package com.klef.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.dev.entity.Charity;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {
}
