package com.example.restdocumentreciever;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static junit.framework.Assert.assertNotNull;

public class DocumentTest {
    private static String createPersonUrl;
    private static String updatePersonUrl;
    private static RestTemplate restTemplate;
    private static HttpHeaders headers;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeClass
    public static void runBeforeAllTestMethods() throws JSONException, JsonProcessingException {
        createPersonUrl = "http://localhost:8000/createDocument";
        updatePersonUrl = "http://localhost:8000/updateDocument";
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull() throws IOException {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/request.json"), "UTF-8");
        HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
        String personResultAsJsonStr = restTemplate.postForObject(createPersonUrl, request, String.class);
        JsonNode root = objectMapper.readTree(personResultAsJsonStr);

        assertNotNull(personResultAsJsonStr);
        assertNotNull(root);
        assertNotNull(root.path("name").asText());
    }

}
