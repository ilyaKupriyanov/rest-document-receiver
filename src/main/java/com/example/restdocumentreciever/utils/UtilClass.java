package com.example.restdocumentreciever.utils;

import com.example.restdocumentreciever.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

@Component
public class UtilClass {

    public String getNameOfProductWithError(ObjectError objectError, Product[] products) {
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


