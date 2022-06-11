package org.isa.holidaysweb.service;

import org.isa.holidaysweb.exception.RecordException;
import org.isa.holidaysweb.model.EmployeeEntity;
import org.isa.holidaysweb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> result = (List<EmployeeEntity>) repository.findAll();
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<>();
		}
	}
	
	public EmployeeEntity getEmployeeById(Long id) throws RecordException {
		Optional<EmployeeEntity> employee = repository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordException("No employee record exist for given id");
		}
	}
	
	public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) {
		if(entity.getId()  == null) {
			return repository.save(entity);
		}
		else {
			Optional<EmployeeEntity> employee = repository.findById(entity.getId());
			if(employee.isPresent()) {
				EmployeeEntity newEntity = employee.get();
				newEntity.setDepartment(entity.getDepartment());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());
				newEntity.setRegistry(entity.getRegistry());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				return repository.save(entity);
			}
		}
	} 
	
	public void deleteEmployeeById(Long id) throws RecordException {
		Optional<EmployeeEntity> employee = repository.findById(id);
		if(employee.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordException("No employee record exist for given id");
		}
	} 
}