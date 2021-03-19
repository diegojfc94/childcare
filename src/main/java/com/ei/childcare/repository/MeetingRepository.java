package com.ei.childcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ei.childcare.entity.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

	public List<Meeting> findAllByOrderByInitDateDesc();
	
}
