package com.nikesh.cms.APITest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nikesh.cms.controller.CustomerController;
import com.nikesh.cms.model.Customer;
import com.nikesh.cms.repository.CustomerRepository;
import com.nikesh.cms.service.CustomerService;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GetRequestTest {

	@MockBean
	private CustomerService service;
	
	@MockBean 
	private CustomerRepository repository;

	@Autowired
	private MockMvc mockMvc;

	//url = "http://localhost:8080/cms/{id}";
	
	private static String output="""
			{
				"id":1,
				"firstName":"Nikesh",
				"lastName":"Chauhan",
				"nickName":"nik",
				"sex":"M",
				"age":22,
				"qualification":"BE",
				"permanentAddress":"u87tfcgjiy8 6tygh6tg uyfghyg",
				"communicationAddress":"yfhguyrtdc utghgtfc iytgcvgf",
				"statePin":"400078",
				"notes":"knows how to code"

			}
			""";

	@Test
	void getRequestPositive() throws Exception{

		
		MockHttpServletRequestBuilder request=MockMvcRequestBuilders.get("http://localhost:8080/cms/1").accept(MediaType.APPLICATION_JSON);
		Customer customer =new Customer(1l,"Nikesh","Chauhan","nik",'M',22,"BE","u87tfcgjiy8 6tygh6tg uyfghyg","yfhguyrtdc utghgtfc iytgcvgf","400078","knows how to code");
		when(service.findCustomerById(1l)).thenReturn(customer);
		MvcResult result=mockMvc.perform(request).andReturn();
		
		
		MockHttpServletResponse response=result.getResponse();
		assertEquals(200,response.getStatus());
		JSONAssert.assertEquals(output, response.getContentAsString(), false);
		
	}
	
	@Test
	void getRequestNegative() throws Exception{
		
		MockHttpServletRequestBuilder request=MockMvcRequestBuilders.get("http://localhost:8080/cms/2").accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(request).andReturn();
		assertEquals(404,result.getResponse().getStatus());
		
	}

}
