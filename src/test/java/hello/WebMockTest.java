package hello;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GreetingController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.greet()).thenReturn("Hello, Mock");
        this.mockMvc.perform(get("/greeting/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mock")));
    }
    @Test
    public void usernameCanBeSet() throws Exception {
//        this.mockMvc.perform(post("/user").content("Maruf").contentType(MediaType.TEXT_PLAIN)).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Maruf")));
        ObjectMapper mapper =  new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new String("Maruf"));
        this.mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status()
                .isOk()).andExpect(content().string(containsString("Maruf")));
    }
    @Test
    public void greetingShouldReturnMessageFromServiceWithUsername() throws Exception {
        service.setUsername("Test");
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello my dear, lovely, World!\nYou entered under following username: Maruf")));
    }
}
