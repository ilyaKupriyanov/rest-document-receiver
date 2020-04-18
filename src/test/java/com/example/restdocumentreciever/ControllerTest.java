package com.example.restdocumentreciever;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RestDocumentReceiverApplication.class)
@DisplayName("Тестирование работы контроллера с бизнес-документом")
public class ControllerTest {

    private MockMvc mockMvc;

    private static final String BASE_URL = "/createDocument";

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Позитивный сценарий")
    public void testPositiveScenario() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/request.json"), "UTF-8");

        mockMvc.perform(
        post(BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status", is("SUCCESS")))
        .andExpect(jsonPath("$.errorMessages").isEmpty());
    }

    @Test
    @DisplayName("Сценарий с пустыми seller и customer")
    public void testNotCorrectSeller() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/request_not_corr_seller.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages[0]",is("Поле seller должно содержать 9 символов")));
    }

    @Test
    @DisplayName("Сценарий с пустыми seller и customer")
    public void testNotCorrectCustomer() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/request_not_corr_customer.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages[*]",is("Поле customer должно содержать 9 символов")));
    }


}
