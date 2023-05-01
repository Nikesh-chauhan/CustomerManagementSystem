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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nikesh.cms.controller.CustomerController;
import com.nikesh.cms.service.CustomerService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@WebMvcTest(controllers=CustomerController.class)
public class Getrequests{
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private CustomerService service;
//	
//	private static String url="http://localhost:8080/cms/6";
//	
//	@Test
//	public void ReadPositive() throws Exception{
//		
//		Customer customer=new Customer
//		
//		ResultActions response= mockMvc.perform(get("http://localhost:8080/cms/6"));
//		
//	}
	
}

