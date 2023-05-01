package com.nikesh.cms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikesh.cms.model.Customer;
import com.nikesh.cms.repository.CustomerRepository;

@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepository repository;
	
	/*finds customer by id from database
	 * */
	public Customer findCustomerById(Long id) {
		
		Optional<Customer> custOpt= repository.findById(id);						//finds customer from database by id
		if(!custOpt.isEmpty()) {													//if found custOpt is not empty
			Customer foundCustomer=custOpt.get();									//then gets the customer from optional
			return foundCustomer;													// returns the customer object
		}
		return null;																//if custOpt is null returns null  
	}

	/*used to restrict changing of id
	 * 
	public Customer updateCustomerDetails(Customer foundCustomer,Customer customer) {

		foundCustomer.setFirstName(customer.getFirstName());
		foundCustomer.setLastName(customer.getLastName());
		foundCustomer.setNickName(customer.getNickName());
		foundCustomer.setSex(customer.getSex());
		foundCustomer.setAge(customer.getAge());
		foundCustomer.setQualification(customer.getQualification());
		foundCustomer.setPermanentAddress(customer.getPermanentAddress());
		foundCustomer.setCommunicationAddress(customer.getCommunicationAddress());
		foundCustomer.setStatePin(customer.getStatePin());
		foundCustomer.setNotes(customer.getNotes());
		
		repository.save(foundCustomer);
		
		return foundCustomer;
		
	}*/

}
