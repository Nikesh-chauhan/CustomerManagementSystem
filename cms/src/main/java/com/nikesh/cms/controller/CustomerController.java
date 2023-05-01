package com.nikesh.cms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikesh.cms.exceptionhandling.CustomerNotFoundException;
import com.nikesh.cms.model.Customer;
import com.nikesh.cms.repository.CustomerRepository;
import com.nikesh.cms.service.CustomerService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/cms")
public class CustomerController {
	
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	CustomerService service;
	
	
	/*used to save data in the Database if the data is valid
	 * */
	@PostMapping("/save")
	public ResponseEntity<Customer> Create(@Valid@RequestBody Customer customer, BindingResult result){
		
		if(result.hasErrors()) {																//checks if there are errors
			FieldError fieldError = result.getFieldError();										//gets the field errors
			throw new ValidationException(fieldError.getDefaultMessage());						//throws validation exception with default message 
		}
		if(customer.getSex()!=0) {																// checks if cutsomer sex is 0
		repository.save(customer);																// saves customer to database
		}else{throw new ValidationException("Sex is required field");}							// throws validation exception with default message
		
		return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);						// returns json body with status code
		
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*used to fetch data from database if present and throw an exception if no data present with given id
	 * */
	@GetMapping("/{id}")
	public ResponseEntity<Customer> Read(@PathVariable("id") Long id){
		
		Customer foundCustomer = service.findCustomerById(id);									//finds customer by id
		if(foundCustomer== null) {throw new CustomerNotFoundException("CUSTOMER NOT FOUND");}	//if foundcustomer is null i.e. no customer found with given id
																								//then throws an exception
		return new ResponseEntity<Customer>(foundCustomer,HttpStatus.OK);						// returns response entity with status code
	
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*used to update the data on basis of the id
	 * */
	@PutMapping("/save/{id}")
	public ResponseEntity<Customer> Update(@PathVariable("id") Long id,@Valid@RequestBody Customer customer,BindingResult result){
		
		if(result.hasErrors()) {																//checks if there are errors
			FieldError fieldError = result.getFieldError();										//gets the field errors	
			throw new ValidationException(fieldError.getDefaultMessage());						//throws validation exception with default message 
		}
		
		if(customer.getSex()==0) {throw new ValidationException("Sex is required field");}		// if sex is null throws validation exception with default message
		
		Customer foundCustomer = service.findCustomerById(id);									//finds customer by id
		if(foundCustomer!=null) {																//if foundcustomer not is null i.e. customer found with given id
			//service.updateCustomerDetails(foundCustomer,customer);                			/* this method can be used if updating id isn't allowed*/
			repository.save(customer);															//saves the customer to database
		}else {throw new CustomerNotFoundException("CUSTOMER NOT FOUND");}						//if no customer found then throws an exception
		
		return new  ResponseEntity<Customer>(customer,HttpStatus.CREATED);
		
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*checks if the given id is present, if not the throws error , if present then deletes that record from database
	 * */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> Delete(@PathVariable("id") Long id){
		
		Customer foundCustomer = service.findCustomerById(id);									//finds customer by id
		if(foundCustomer!=null) {																//if foundcustomer not is null i.e. customer found with given id
			repository.deleteById(id);															//deletes customer from database
		}else {throw new CustomerNotFoundException("CUSTOMER NOT FOUND");}						//if no customer found then throws an exception
		
		return new  ResponseEntity<Customer>(HttpStatus.ACCEPTED);								// returns status code
		
		
	}
	

}

/*
 * {
    "firstName":"Nikesh",
	"lastName":"Chauhan",
	"sex":"M",
	"age":"22",
	"qualification":"BE",
	"permanentAddress":"u87tfcgjiy8 6tygh6tg uyfghyg",
	"communicationAddress":"yfhguyrtdc utghgtfc iytgcvgf",
	"statePin":"400078",
	"notes":"knows how to code"

}*/
