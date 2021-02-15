package com.guid.dbperformtests.repositories;

import com.guid.dbperformtests.models.HBMillion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("hbMillionRepository")
public interface HBMillionRepository extends JpaRepository<HBMillion, Long> {
    
}
