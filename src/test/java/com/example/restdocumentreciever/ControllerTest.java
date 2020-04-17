package com.example.restdocumentreciever;

import com.example.restdocumentreciever.controller.DocumentController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest
public class ControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new DocumentController()).build();
    }

    @Test
    @DisplayName("Если нет поля name, то возвращается код 400")
    public void addTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/createDocument")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content("{\"seller\":\"123534251\", \"customer\":\"648563524\", \"products\":[{\"name\":\"milk\",\"code\":\"2364758363546\"},{\"name\":\"water\",\"code\":\"3656352437590\"}]}"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
