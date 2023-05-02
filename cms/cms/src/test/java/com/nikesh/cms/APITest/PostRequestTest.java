package com.nikesh.cms.APITest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import com.nikesh.cms.controller.CustomerController;
import com.nikesh.cms.model.Customer;
import com.nikesh.cms.repository.CustomerRepository;
import com.nikesh.cms.service.CustomerService;

@WebMvcTest(controllers=CustomerController.class)
@AutoConfigureMockMvc(addFilters=false)
public class PostRequestTest {
	
	@MockBean
	private CustomerService service;
	
	@MockBean
	private CustomerRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private static String json="""
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
	
	private static String json2="""
			{
				"id":1,
				"firstName":"",
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
	void postRequestPositive() throws Exception{
		
		Customer customer =new Customer(1l,"Nikesh","Chauhan","nik",'M',22,"BE","u87tfcgjiy8 6tygh6tg uyfghyg","yfhguyrtdc utghgtfc iytgcvgf","400078","knows how to code");
		
		when(service.saveCustomer(customer)).thenReturn(customer);
		MockHttpServletRequestBuilder request =MockMvcRequestBuilders.post("http://localhost:8080/cms/save").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result =mockMvc.perform(request).andReturn();
		assertEquals(201, result.getResponse().getStatus());
		
		JSONAssert.assertEquals(json, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	void postRequestNegative() throws Exception{
		
		Customer customer =new Customer(1l,"Nikesh","","nik",'M',22,"BE","u87tfcgjiy8 6tygh6tg uyfghyg","yfhguyrtdc utghgtfc iytgcvgf","400078","knows how to code");
		
		MockHttpServletRequestBuilder request =MockMvcRequestBuilders.post("http://localhost:8080/cms/save").accept(MediaType.APPLICATION_JSON).content(json2).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result =mockMvc.perform(request).andReturn();
		assertEquals(400, result.getResponse().getStatus());
		
		//JSONAssert.assertEquals(output, result.getResponse().getContentAsString(), false);
		
	}
	

}
