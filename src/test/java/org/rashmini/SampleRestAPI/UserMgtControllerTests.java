package org.rashmini.SampleRestAPI;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.rashmini.SampleRestAPI.model.User;
import org.rashmini.SampleRestAPI.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserMgtController.class)
public class UserMgtControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserMgtService userMgtService;

	List<User> mockUser = new ArrayList<>(Arrays.asList(new User("6df6cf47-af1e-47cd-a978-8720bce9dac1", "Smith", "Jane", "jane@mail.com")));

	@Test
	public void getUsersBySurname() throws Exception {

		Mockito.when(userMgtService.getUsersBySurname(Mockito.anyString())).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/users/Smith")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":\"6df6cf47-af1e-47cd-a978-8720bce9dac1\",\"surname\":\"Smith\",\"firstName\":\"Jane\",\"email\":\"jane@mail.com\"}]";

		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void getUsers() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.get("/users")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
	}

	@Test
	public void addUser() throws Exception {

		User user = new User("7e43b819-1383-4de1-877c-7e0bc15e3998", "firstName", "lastName", "admin@mail.com");

		mockMvc.perform( MockMvcRequestBuilders
				.post("/users")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
