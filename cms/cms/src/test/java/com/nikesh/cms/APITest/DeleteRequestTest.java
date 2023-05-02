package com.nikesh.cms.APITest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nikesh.cms.controller.CustomerController;
import com.nikesh.cms.model.Customer;
import com.nikesh.cms.repository.CustomerRepository;
import com.nikesh.cms.service.CustomerService;

@WebMvcTest(controllers=CustomerController.class)
@AutoConfigureMockMvc(addFilters=false)
public class DeleteRequestTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService service;
	
	@MockBean
	private CustomerRepository repository;
	
	
	@Test
	void deleteRequestPositive() throws Exception{
		
		Customer customer =new Customer(1l,"Nikesh","Chauhan","nik",'M',22,"BE","u87tfcgjiy8 6tygh6tg uyfghyg","yfhguyrtdc utghgtfc iytgcvgf","400078","knows how to code");
		
		when(service.findCustomerById(1l)).thenReturn(customer);
		when(service.deleteCustomer(1l)).thenReturn("Customer Deleted Successfully");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("http://localhost:8080/cms/delete/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response=result.getResponse();
		assertEquals(202,response.getStatus());
		
	}
	
	@Test
	void deleteRequestNegative() throws Exception{
		
		Customer customer =new Customer(1l,"Nikesh","Chauhan","nik",'M',22,"BE","u87tfcgjiy8 6tygh6tg uyfghyg","yfhguyrtdc utghgtfc iytgcvgf","400078","knows how to code");
		
		when(service.findCustomerById(1l)).thenReturn(null);
		when(service.deleteCustomer(1l)).thenReturn("Customer Deleted Successfully");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("http://localhost:8080/cms/delete/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response=result.getResponse();
		assertEquals(404,response.getStatus());
		
	}

}
