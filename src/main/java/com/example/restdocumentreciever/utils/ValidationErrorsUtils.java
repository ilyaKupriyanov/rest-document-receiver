package com.example.restdocumentreciever.utils;

import com.example.restdocumentreciever.model.BusinessDocument;
import com.example.restdocumentreciever.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationErrorsUtils {

    public List<String> checkDocumentForValidationErrors(BusinessDocument businessDocument, Errors errors) {
        List<String> errorMessagesList = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                String message = objectError.getDefaultMessage();
                if (businessDocument.getProducts().length != 0) {
                    if (objectError.getCodes()[0].contains("products")) {
                        String name = getNameOfProductWithError(objectError, businessDocument.getProducts());
                        message = message.concat(name);
                    }
                }
                errorMessagesList.add(message);
            }
        }
        if (businessDocument.getProducts().length == 0) {
            errorMessagesList.add("Список products пуст");
        }
        return errorMessagesList;
    }

    private String getNameOfProductWithError(ObjectError objectError, Product[] products) {
        String errorCode = objectError.getCodes()[0];
        int number = parseNumberOfProduct(errorCode);
        Product errorProduct = products[number];
        return errorProduct.getName();
    }

    private int parseNumberOfProduct(String text) {
        String value="";
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                value = String.valueOf(text.charAt(i));
            }
        }
        return Integer.parseInt(value);
    }

}


