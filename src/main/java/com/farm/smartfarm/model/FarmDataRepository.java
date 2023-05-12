package com.farm.smartfarm.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmDataRepository extends JpaRepository<FarmData, String> {
}
