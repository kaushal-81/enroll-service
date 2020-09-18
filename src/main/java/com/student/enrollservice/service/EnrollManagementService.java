package com.student.enrollservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.student.enrollservice.bean.EnrollRequestBean;
import com.student.enrollservice.bean.EnrolleeDependentBean;
import com.student.enrollservice.domain.EnrollDependentEntity;
import com.student.enrollservice.domain.EnrollEntity;
import com.student.enrollservice.repository.EnrolleeDependentRepository;
import com.student.enrollservice.repository.EnrolleeRepository;

@Service
public class EnrollManagementService {

	
	@Autowired
	EnrolleeRepository enrolleRepo;

	@Autowired
	EnrolleeDependentRepository dependentRepo;
	
	

	public String addEnrollee(EnrollRequestBean request) {

		Date todayDate = new Date();

		EnrollEntity entity = new EnrollEntity();
		entity.setActivationStatus(true);
		if (request.getDateOfBirth() != null) {
			entity.setDateOfBirth(request.getDateOfBirth());
		} else {
			throw new ResourceNotFoundException("Please enter Date of Birth");
		}
		entity.setEnrollDate(todayDate);
		if (request.getEnrolleeName() != null) {
			entity.setEnrolleeName(request.getEnrolleeName());
		} else {
			throw new ResourceNotFoundException("Please enter enrollee name");
		}
		if (request.getDependentDetails() != null && request.getDependentDetails().size() != 0) {
			entity.setHasDependencies(true);
		} else {
			entity.setHasDependencies(false);
		}
		
		boolean phNumVer=false;
		if (request.getPhoneNumber()!=null) {
			phNumVer=isNumbers(request.getPhoneNumber());
		}
		
		if (phNumVer) {
			String phone=new String(request.getPhoneNumber());
			if(phone.length()<11) {
				entity.setPhoneNumber(request.getPhoneNumber());
			}else {
				throw new ResourceNotFoundException("Phone number length cannot be greater than 12");
			}
		}else {
			entity.setPhoneNumber("");
		}
		
	
		EnrollEntity afterSave = enrolleRepo.save(entity);

		if (request.getDependentDetails()!=null && request.getDependentDetails().size() != 0) {
			saveEnrolleDependents(request, afterSave.getEnrolleId());
		}

		return "Added new enrollee successfully";

	}

	private void saveEnrolleDependents(EnrollRequestBean request, int enrolleId) {

		List<EnrollDependentEntity> dependEntityList = new ArrayList<EnrollDependentEntity>();

		request.getDependentDetails().forEach(a -> {
			EnrollDependentEntity entity = new EnrollDependentEntity();

			entity.setEnrolleId(enrolleId);
			entity.setDateOfBirth(a.getDependentDOB());
			entity.setName(a.getDependentName());
			dependEntityList.add(entity);
		});
		dependentRepo.saveAll(dependEntityList);
	}

	public String modifyEnrollee(int enrolleId, EnrollRequestBean request) {

		EnrollEntity entity = enrolleRepo.findById(enrolleId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrolle Id not found"));

		entity.setActivationStatus(request.getActivationStatus());
		entity.setDateOfBirth(request.getDateOfBirth());
		entity.setEnrolleeName(request.getEnrolleeName());
		if (request.getDependentDetails().size() != 0) {
			entity.setHasDependencies(true);
		} else {
			entity.setHasDependencies(false);
		}
		boolean phNumVer=false;
		if (request.getPhoneNumber()!=null) {
			phNumVer=isNumbers(request.getPhoneNumber());
		}
		
		if (phNumVer) {
			String phone=new String(request.getPhoneNumber());
			if(phone.length()<11) {
				entity.setPhoneNumber(request.getPhoneNumber());
			}else {
				throw new ResourceNotFoundException("Phone number length cannot be greater than 12");
			}
		}else {
			entity.setPhoneNumber("");
		}
		EnrollEntity afterSave = enrolleRepo.save(entity);

		if (request.getDependentDetails() != null && request.getDependentDetails().size() != 0) {
			saveEnrolleDependents(request, afterSave.getEnrolleId());
		}

		return "Enrollee details modified Successfully";
	}

	public String removeEnrollee(int enrolleId) {

		List<EnrollDependentEntity> dependentList = dependentRepo.findByEnrolleId(enrolleId);
		dependentList.forEach(k -> {
			dependentRepo.deleteById(k.getId());
		});

		EnrollEntity entity = enrolleRepo.findById(enrolleId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrolle Id not found"));

		enrolleRepo.delete(entity);

		return "Remove an enrollee entirely";
	}

	public String addDependents(int enrolleId, List<EnrolleeDependentBean> request) {
		// TODO Auto-generated method stub

		EnrollEntity entity = enrolleRepo.findById(enrolleId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrolle Id not found"));
		entity.setHasDependencies(true);
		enrolleRepo.save(entity);

		if (request != null && request.size() != 0) {
			List<EnrollDependentEntity> dependEntityList = new ArrayList<EnrollDependentEntity>();

			request.forEach(a -> {
				EnrollDependentEntity dependentEntity = new EnrollDependentEntity();

				dependentEntity.setEnrolleId(enrolleId);
				dependentEntity.setDateOfBirth(a.getDependentDOB());
				dependentEntity.setName(a.getDependentName());
				dependEntityList.add(dependentEntity);
			});
			dependentRepo.saveAll(dependEntityList);

		} else {
			throw new ResourceNotFoundException("Dependent details cannot be empty");
		}

		return "Added Dependent details Successfully";
	}

	public String deleteDependents(int enrolleId) {

		List<EnrollDependentEntity> dependentList = dependentRepo.findByEnrolleId(enrolleId);
		dependentList.forEach(k -> {
			dependentRepo.deleteById(k.getId());
		});

		EnrollEntity entity = enrolleRepo.findById(enrolleId)
				.orElseThrow(() -> new ResourceNotFoundException("Enrolle Id not found"));
		entity.setHasDependencies(false);
		enrolleRepo.save(entity);

		return "Remove dependents from an enrollee";
	}

	public String modifyDependents(int dependentId, EnrolleeDependentBean dependentDetails) {

		EnrollDependentEntity entity = dependentRepo.findById(dependentId)
				.orElseThrow(() -> new ResourceNotFoundException("Dependent Id not found"));
		entity.setDateOfBirth(dependentDetails.getDependentDOB());
		entity.setName(dependentDetails.getDependentName());

		dependentRepo.save(entity);

		return "Modify existing dependents";
	}
	
	 private boolean isNumbers(String phoneNumber) {
	        boolean numberFlag = false;
	        try {
	            Double numberCheck = Double.parseDouble(phoneNumber);
	            numberFlag = true;
	        } catch (NumberFormatException e) {
	            numberFlag = false;
	        }
	        return numberFlag;
	    }

	public List<EnrollRequestBean> getEnrollees() {
		
		 List<EnrollEntity> list=	enrolleRepo.findAll();
		 List<EnrollRequestBean> enrollList=new ArrayList<EnrollRequestBean>();
		 list.forEach(a -> {
			 EnrollRequestBean bean =new EnrollRequestBean();
			 bean.setActivationStatus(a.isActivationStatus());
			 bean.setDateOfBirth(a.getDateOfBirth());
			 bean.setEnrolleeName(a.getEnrolleeName());
			 bean.setPhoneNumber(a.getPhoneNumber());
			 List<EnrollDependentEntity> dependentList = dependentRepo.findByEnrolleId(a.getEnrolleId());
			 List<EnrolleeDependentBean> depBeanList=new ArrayList<EnrolleeDependentBean>();
			 dependentList.forEach(k->{
				 EnrolleeDependentBean depBean=new EnrolleeDependentBean();
				 depBean.setDependentDOB(k.getDateOfBirth());
				 depBean.setDependentName(k.getName());
				 depBeanList.add(depBean);
			 });
			 bean.setDependentDetails(depBeanList);
			 enrollList.add(bean);
			 
		 });
		 
		return enrollList;
	}

}
