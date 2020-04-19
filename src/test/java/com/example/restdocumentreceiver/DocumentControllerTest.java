package com.example.restdocumentreceiver;

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
public class DocumentControllerTest {

    private MockMvc mockMvc;

    private static final String BASE_URL = "/createDocument";
    private static final String SELLER_NOT_CORRECT_MESSAGE = "Поле seller должно содержать 9 символов";
    private static final String CUSTOMER_NOT_CORRECT_MESSAGE = "Поле customer должно содержать 9 символов";
    private static final String MILK_NOT_CORRECT_CODE_MESSAGE = "Поле code должно содержать 13 символов для товара milk";
    private static final String WATER_NOT_CORRECT_CODE_MESSAGE = "Поле code должно содержать 13 символов для товара water";
    private static final String PRODUCT_NOT_CORRECT_CODE_MESSAGE = "Поле code должно содержать 13 символов для товара";
    private static final String PRODUCT_NOT_CORRECT_NAME_MESSAGE = "Поле name не должно быть пустым для товара №";
    private static final String PRODUCTS_EMPTY_MESSAGE = "Список products пуст";
    private static final String BAD_DOC_MESSAGE = "Некорректная структура документа";

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Позитивный сценарий")
    public void testPositiveScenario() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_correct.json"), "UTF-8");
        mockMvc.perform(
        post(BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status", is("SUCCESS")))
        .andExpect(jsonPath("$.errorMessages").isEmpty());
    }

    @Test
    @DisplayName("Сценарий с некорректными seller и customer")
    public void testNotCorrectSeller() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_not_corr_seller_and_customer.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(2)))
                .andExpect(jsonPath("$.errorMessages.[0]", is(CUSTOMER_NOT_CORRECT_MESSAGE)))
                .andExpect(jsonPath("$.errorMessages.[1]", is(SELLER_NOT_CORRECT_MESSAGE)));
    }

    @Test
    @DisplayName("Сценарий с пустыми seller и customer")
    public void testEmptySellerAndCustomer() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_empty_seller_and_customer.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(2)))
                .andExpect(jsonPath("$.errorMessages[0]", is(CUSTOMER_NOT_CORRECT_MESSAGE)))
                .andExpect(jsonPath("$.errorMessages[1]", is(SELLER_NOT_CORRECT_MESSAGE)));
    }

    @Test
    @DisplayName("Сценарий с некорректными products")
    public void testNotCorrectProducts() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_not_corr_products.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(2)))
                .andExpect(jsonPath("$.errorMessages[0]", is(MILK_NOT_CORRECT_CODE_MESSAGE)))
                .andExpect(jsonPath("$.errorMessages[1]", is(WATER_NOT_CORRECT_CODE_MESSAGE)));
    }

    @Test
    @DisplayName("Сценарий с пустым списком product")
    public void testEmptyProducts() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_empty_products.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(1)))
                .andExpect(jsonPath("$.errorMessages[0]", is(PRODUCTS_EMPTY_MESSAGE)));
    }

    @Test
    @DisplayName("Сценарий, когда один элемент в products имеет пустое имя")
    public void testProductsWhenOneProductHasEmptyName() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_when_product_has_empty_name.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(1)))
                .andExpect(jsonPath("$.errorMessages[0]", is(PRODUCT_NOT_CORRECT_NAME_MESSAGE+"2")));
    }

    @Test
    @DisplayName("Сценарий, когда один элемент в products имеет пустое имя и при этом имеет некорректный код")
    public void testProductsWhenOneProductHasEmptyNameAndIncorrectCode() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_when_product_has_empty_name_and_not_corr_code.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(2)))
                .andExpect(jsonPath("$.errorMessages[0]", is(PRODUCT_NOT_CORRECT_CODE_MESSAGE+" №1")))
                .andExpect(jsonPath("$.errorMessages[1]", is(PRODUCT_NOT_CORRECT_NAME_MESSAGE+"1")));
    }

    @Test
    @DisplayName("Сценарии с нарушенной структурой документа")
    public void testNotCorrectDocument() throws Exception {
        //Отсутствие seller
        String requestBody1 = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/badStructure/doc_without_seller.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody1))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(1)))
                .andExpect(jsonPath("$.errorMessages[0]", is(BAD_DOC_MESSAGE)));

        //Отсутствие customer
        String requestBody2 = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/badStructure/doc_without_customer.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody2))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(1)))
                .andExpect(jsonPath("$.errorMessages[0]", is(BAD_DOC_MESSAGE)));

        //Отсутствие products
        String requestBody3 = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/badStructure/doc_without_products.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody3))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(1)))
                .andExpect(jsonPath("$.errorMessages[0]", is(BAD_DOC_MESSAGE)));

        //Пустой документ
        String requestBody4 = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/badStructure/empty_doc.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody4))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(1)))
                .andExpect(jsonPath("$.errorMessages[0]", is(BAD_DOC_MESSAGE)));
    }

    @Test
    @DisplayName("Комбинированный сценарий")
    public void testCombinedScenario() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResourceAsStream("/testDocuments/doc_very_incorrect.json"), "UTF-8");
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("VALIDATION_ERROR")))
                .andExpect(jsonPath("$.errorMessages.size()", is(5)))
                .andExpect(jsonPath("$.errorMessages[0]", is(WATER_NOT_CORRECT_CODE_MESSAGE)))
                .andExpect(jsonPath("$.errorMessages[1]", is(PRODUCT_NOT_CORRECT_CODE_MESSAGE+" №1")))
                .andExpect(jsonPath("$.errorMessages[2]", is(CUSTOMER_NOT_CORRECT_MESSAGE)))
                .andExpect(jsonPath("$.errorMessages[3]", is(PRODUCT_NOT_CORRECT_NAME_MESSAGE+"1")))
                .andExpect(jsonPath("$.errorMessages[4]", is(SELLER_NOT_CORRECT_MESSAGE)));
    }

}