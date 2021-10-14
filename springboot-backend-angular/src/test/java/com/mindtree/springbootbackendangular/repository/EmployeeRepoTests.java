package com.mindtree.springbootbackendangular.repository;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.springbootbackendangular.model.Employee;
import com.sun.tools.javac.util.Assert;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.ForJnaSolarisDoor.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepoTests {

	private MockMvc mockMvc;
	private ObjectMapper om = new ObjectMapper();
	@Autowired(required = true)
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setId(117);
		employee.setFirstName("Radhika");
		employee.setLastName("Gupta");
		employee.setEmailId("radhika@gmail.com");
		String request = om.writeValueAsString(employee);
		MvcResult result = mockMvc
				.perform(post("/EMS/add").content(request).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}

	@Test
	public void getAllEmployeeTest() throws Exception {
		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.get("/EMS/getAllEmployee").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}

	@Test
	public void getEmployeeTest() throws Exception {
		int id = 234;
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/EMS/getEmployee/" + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}

	@Test
	public void deleteEmployeeTest() throws Exception {
		int id = 512;
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/EMS/delete/" + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == true);
	}
}
