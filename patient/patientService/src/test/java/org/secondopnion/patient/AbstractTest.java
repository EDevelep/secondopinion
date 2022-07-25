package org.secondopnion.patient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secondopinion.patient.controller.AilmentsController;
import org.secondopinion.patient.dto.Ailments;
import org.secondopinion.patient.service.impl.AilmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AilmentsController.class)
public class AbstractTest  extends PatientApplicationTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private AilmentServiceImpl alAilmentServiceImpl;

	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	private static ObjectMapper mapper = new ObjectMapper();

	
}
