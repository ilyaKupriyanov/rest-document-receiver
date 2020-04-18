package com.example.restdocumentreciever;

import com.example.restdocumentreciever.controller.DocumentController;
import com.example.restdocumentreciever.utils.ValidationErrorsUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = DocumentController.class)
@DisplayName("Тестирование контроллера работы с бизнес-сообщением")
public class ControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new DocumentController(new ValidationErrorsUtils())).build();
    }

    @Test
    @DisplayName("Позитивный сценарий")
    public void testPositive() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/request.json"), "UTF-8");

        mockMvc.perform(
        post("/createDocument")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
