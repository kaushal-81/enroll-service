package com.student.enrollservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.student.enrollservice.bean.EnrollRequestBean;
import com.student.enrollservice.bean.EnrolleeDependentBean;
import com.student.enrollservice.domain.EnrollDependentEntity;
import com.student.enrollservice.domain.EnrollEntity;
import com.student.enrollservice.service.EnrollManagementService;

class EnrollControllerTest {
	
	@InjectMocks
	EnrollController controller;
	
	@Mock
	EnrollManagementService service;
	
	EnrollEntity enrollEntity;
	
	EnrollDependentEntity dependEntity;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		enrollEntity=new EnrollEntity();
		enrollEntity.setActivationStatus(true);
		Date todayDate=new Date();
		enrollEntity.setDateOfBirth(todayDate);
		enrollEntity.setEnrollDate(todayDate);
		enrollEntity.setEnrolleeName("Test");
		enrollEntity.setHasDependencies(true);
		enrollEntity.setPhoneNumber("34123123");
		
		dependEntity=new EnrollDependentEntity();
		dependEntity.setDateOfBirth(todayDate);
		dependEntity.setName("TestDependent");
		
	}

	@Test
	void testAddEnrollee() {
		EnrollRequestBean req=new EnrollRequestBean();
		
		req.setActivationStatus(true);
		Date todayDate=new Date();
		req.setDateOfBirth(todayDate);
		req.setEnrolleeName("Test");
		req.setPhoneNumber("34123123");
		List<EnrolleeDependentBean> list=new ArrayList<EnrolleeDependentBean>();
		EnrolleeDependentBean dependent=new EnrolleeDependentBean();
		dependent.setDependentDOB(todayDate);
		dependent.setDependentName("TestDependent");
		list.add(dependent);
		req.setDependentDetails(list);
		when(service.addEnrollee(req)).thenReturn("Added new enrollee successfully");
		
		String resp=controller.addEnrollee(req);
		assertEquals("Added new enrollee successfully", resp);
		
	}

	@Test
	void testModifyEnrollee() {

		int enrolleId = 1;
		EnrollRequestBean req = new EnrollRequestBean();

		req.setActivationStatus(true);
		Date todayDate = new Date();
		req.setDateOfBirth(todayDate);
		req.setEnrolleeName("Test");
		req.setPhoneNumber("34123123");
		List<EnrolleeDependentBean> list = new ArrayList<EnrolleeDependentBean>();
		EnrolleeDependentBean dependent = new EnrolleeDependentBean();
		dependent.setDependentDOB(todayDate);
		dependent.setDependentName("TestDependent");
		list.add(dependent);
		req.setDependentDetails(list);
		when(service.modifyEnrollee(enrolleId, req)).thenReturn("Enrollee details modified Successfully");
		String resp = controller.modifyEnrollee(enrolleId, req);
		assertEquals("Enrollee details modified Successfully", resp);

	}

	@Test
	void testRemoveEnrollee() {
		int enrolleId = 1;
		when(service.removeEnrollee(enrolleId)).thenReturn("Remove an enrollee entirely");
		String resp = controller.removeEnrollee(enrolleId);
		assertEquals("Remove an enrollee entirely", resp);
	}

	@Test
	void testAddDependents() {
		int enrolleId = 1;
		Date todayDate=new Date();
		List<EnrolleeDependentBean> req = new ArrayList<EnrolleeDependentBean>();
		EnrolleeDependentBean dependent = new EnrolleeDependentBean();
		dependent.setDependentDOB(todayDate);
		dependent.setDependentName("TestDependent");
		req.add(dependent);
		when(service.addDependents(enrolleId, req)).thenReturn("Added Dependent details Successfully");
		String resp = controller.addDependents(enrolleId, req);
		assertEquals("Added Dependent details Successfully", resp);
	}

	@Test
	void testDeleteDependents() {
		int enrolleId = 1;
		when(service.deleteDependents(enrolleId)).thenReturn("Remove dependents from an enrollee");
		String resp=controller.deleteDependents(enrolleId);
		assertEquals("Remove dependents from an enrollee", resp);
	}

	@Test
	void testModifyDependents() {
		int dependentId=1;
		Date todayDate=new Date();
		EnrolleeDependentBean dependentDetails = new EnrolleeDependentBean();
		dependentDetails.setDependentDOB(todayDate);
		dependentDetails.setDependentName("TestDependent");
		when(service.modifyDependents(dependentId, dependentDetails)).thenReturn("Modify existing dependents");
		String resp=controller.modifyDependents(dependentId, dependentDetails);
		assertEquals("Modify existing dependents", resp);
	}

	@Test
	void testGetEnrollees() {

		List<EnrollRequestBean> reqList=new ArrayList<EnrollRequestBean>();
		EnrollRequestBean req = new EnrollRequestBean();

		req.setActivationStatus(true);
		Date todayDate = new Date();
		req.setDateOfBirth(todayDate);
		req.setEnrolleeName("Test");
		req.setPhoneNumber("34123123");
		List<EnrolleeDependentBean> list = new ArrayList<EnrolleeDependentBean>();
		EnrolleeDependentBean dependent = new EnrolleeDependentBean();
		dependent.setDependentDOB(todayDate);
		dependent.setDependentName("TestDependent");
		list.add(dependent);
		req.setDependentDetails(list);
		reqList.add(req);
		
		when(service.getEnrollees()).thenReturn(reqList);
		List<EnrollRequestBean> resp = controller.getEnrollees();
		assertEquals(resp, resp);

	}

}
