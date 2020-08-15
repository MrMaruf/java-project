package hello;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldApplicationTest {
    private String testUsername = "testUsername";
    private String testTitle = "testTitle";
    @Test
    public void contextLoads() {

    }
    @Autowired
    private HomeController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void controllerIsNotNull() throws Exception {
        Assertions.assertThat(controller).isNotNull();
    }
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));
    }
    @Test
    public void usernameReturnedAfterSetting() throws Exception {
        this.mockMvc.perform(post("/greeting/user").param("newUsername", testUsername)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("testUsername")));
    }
    @Test
    public void greetingShouldReturnMessageFromServiceWithUsername() throws Exception {
        this.mockMvc.perform(post("/greeting/user").param("newUsername", testUsername));
        this.mockMvc.perform(get("/greeting/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("You entered under following username: "+testUsername)));
    }
    @Test
    public void titleReturnedAfterSetting() throws Exception {
        this.mockMvc.perform(post("/greeting/user").param("newUsername", testUsername));
        this.mockMvc.perform(put("/greeting/user/"+testUsername).param("newTitle", testTitle)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(testTitle)));
    }
    @Test
    public void greetingShouldReturnMessageFromServiceWithTitle() throws Exception {
        this.mockMvc.perform(post("/greeting/user").param("newUsername", testUsername));
        this.mockMvc.perform(put("/greeting/user/"+testUsername).param("newTitle", testTitle));
        this.mockMvc.perform(get("/greeting/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Your title: "+testTitle)));
    }
    @Test
    public void deletingUserShouldResetGreetingToInitialSettings() throws Exception {
        this.mockMvc.perform(post("/greeting/user").param("newUsername", testUsername));
        this.mockMvc.perform(put("/greeting/user/"+testUsername).param("newTitle", testTitle));
        this.mockMvc.perform(delete("/greeting/user/"+testUsername));
        this.mockMvc.perform(get("/greeting/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString(testUsername))))
                .andExpect(content().string(not(containsString(testTitle))));
    }
}