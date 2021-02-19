package com.suho.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class HomeControllerTest {
	
	// Setup with Application Configuraion
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		// Setup with StandAlone
		// 컨트롤러 단독으로 테스트하는 방법
//		this.mvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
		
		// Setup with Application Configuraion
		// 정의된 웹 어플리케이션 컨텍스트를 이용한 방법
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
	}
	
	
	@Test
	public void home() throws Exception {
		
		mvc.perform(get("/"))
		.andExpect(status().isOk());
		
	}
	
}
