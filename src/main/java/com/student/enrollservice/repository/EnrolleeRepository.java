package com.student.enrollservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.student.enrollservice.domain.EnrollEntity;

@Repository
public interface EnrolleeRepository extends JpaRepository<EnrollEntity, Integer>,
		JpaSpecificationExecutor<EnrollEntity>, PagingAndSortingRepository<EnrollEntity, Integer>{

	
}
