package com.student.enrollservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.student.enrollservice.domain.EnrollDependentEntity;

@Repository
public interface EnrolleeDependentRepository extends JpaRepository<EnrollDependentEntity, Integer>,
		JpaSpecificationExecutor<EnrollDependentEntity>, PagingAndSortingRepository<EnrollDependentEntity, Integer>{
	
	List<EnrollDependentEntity> findByEnrolleId(int enrolleId);

	
}
