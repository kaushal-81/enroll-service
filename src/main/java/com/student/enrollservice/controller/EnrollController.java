package com.student.enrollservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.enrollservice.bean.EnrollRequestBean;
import com.student.enrollservice.bean.EnrolleeDependentBean;
import com.student.enrollservice.service.EnrollManagementService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("enroll")
public class EnrollController {
	@Autowired
	private EnrollManagementService service;

	@ApiOperation("Api to add a new enrollee")
	@PostMapping("/addEnrollee")
	public String addEnrollee(@RequestBody() EnrollRequestBean request) {
		String resp= service.addEnrollee(request);
		return resp;
	}

	@ApiOperation("Modify an existing enrollee")
	@PutMapping("/modifyEnrollee")
	public String modifyEnrollee(@RequestParam(name = "enrolleId", required = true) int enrolleId,@RequestBody() EnrollRequestBean request) {
		String resp= service.modifyEnrollee(enrolleId,request);
		 return resp;
	}

	@ApiOperation("Remove an enrollee entirely")
	@DeleteMapping("/removeEnrollee")
	public String removeEnrollee(@RequestParam(name = "enrolleId", required = true) int enrolleId) {
		String resp= service.removeEnrollee(enrolleId);
		return resp;
	}

	@ApiOperation("Add dependents to an enrollee")
	@PostMapping("/addDependents")
	public String addDependents(@RequestParam(name = "enrolleId", required = true) int enrolleId,
			@RequestBody() List<EnrolleeDependentBean> request) {
		 String resp=service.addDependents(enrolleId, request);
		return resp;
	}

	@ApiOperation("Remove dependents from an enrollee")
	@DeleteMapping("/deleteDependents")
	public String deleteDependents(@RequestParam(name = "enrolleId", required = true) int enrolleId) {
		String resp= service.deleteDependents(enrolleId);
		return resp;
	}

	@ApiOperation("Modify existing dependents")
	@PutMapping("/modifyDependents")
	public String modifyDependents(@RequestParam(name = "id", required = true) int dependentId,
			@RequestBody() EnrolleeDependentBean dependentDetails) {
		String resp= service.modifyDependents(dependentId, dependentDetails);
		return resp;
	}
	
	@ApiOperation("To fetch all the enrollees")
	@GetMapping("/getEnrollees")
	public List<EnrollRequestBean> getEnrollees() {

		return service.getEnrollees();
	}
	

}
