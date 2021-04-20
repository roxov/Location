package fr.asterox.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationServiceIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenAUsername_whenTrackLocation_thenReturnOkAndInformationToUser() throws Exception {
		String jsonResponse = mockMvc
				.perform(MockMvcRequestBuilders.get("/trackLocation?userName={userName}", "jo")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals("The calculation of your location is on process", jsonResponse);
	}

	@Test
	public void givenAUsername_whenGetNearbyAttractions_thenReturnFiveAttractions() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getNearbyAttractions?userName={userName}", "jo")
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(5));
	}

}
