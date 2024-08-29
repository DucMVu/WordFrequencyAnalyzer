package ordina.demo;

import ordina.demo.controllers.WordFrequencyAnalyzerController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WordFrequencyAnalyzerController.class)
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void backendStarted() throws Exception {
		mockMvc.perform(get("/api/test")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("backend started"));
	}

	@Test
	void highestFrequency() throws Exception {
		mockMvc.perform(get("/api/highestFrequency")
						.contentType(MediaType.APPLICATION_JSON)
						.param("text","This is a test test tets sentence."))
				.andExpect(status().isOk())
				.andExpect(content().string("2"));
	}

	@Test
	void wordFrequency_lowerCase() throws Exception {
		mockMvc.perform(get("/api/wordFrequency")
						.contentType(MediaType.APPLICATION_JSON)
						.param("text","Coding is hard. Testing is also hard. Hard work pays off.")
						.param("word", "hard"))
				.andExpect(status().isOk())
				.andExpect(content().string("3"));
	}

	@Test
	void wordFrequency_upperCase() throws Exception {
		mockMvc.perform(get("/api/wordFrequency")
						.contentType(MediaType.APPLICATION_JSON)
						.param("text","Coding is hard. Testing is also hard. Hard work pays off.")
						.param("word", "Hard"))
				.andExpect(status().isOk())
				.andExpect(content().string("0"));
	}

	@Test
	void wordFrequency_noResult() throws Exception {
		mockMvc.perform(get("/api/wordFrequency")
						.contentType(MediaType.APPLICATION_JSON)
						.param("text","Coding is hard. Testing is also hard. Hard work pays off.")
						.param("word", "keywordSearch"))
				.andExpect(status().isOk())
				.andExpect(content().string("0"));
	}

	@Test
	void mostFrequentWords() throws Exception {
		mockMvc.perform(get("/api/mostFrequentWords")
						.contentType(MediaType.APPLICATION_JSON)
						.param("text","This is a test Test text. Test is hard. Test is fun.")
						.param("limit", "2"))
				.andExpect(status().isOk())
				.andExpect(content().string("[{\"test\":4},{\"is\":3}]"));
	}
}
