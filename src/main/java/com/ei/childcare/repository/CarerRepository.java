package com.ei.childcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ei.childcare.entity.Carer;

@Repository
public interface CarerRepository extends JpaRepository<Carer, Long> {
	
}
