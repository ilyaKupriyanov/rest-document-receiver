package com.example.restdocumentreceiver.utils;

import com.example.restdocumentreceiver.model.BusinessDocument;
import com.example.restdocumentreceiver.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationErrorsUtils {

    private static final String PRODUCT_NAME_EMPTY_MESSAGE = "Поле name не должно быть пустым для товара №";
    private static final String PRODUCT_CODE_NOT_CORRECT_MESSAGE = "Поле code должно содержать 13 символов для товара";

    public List<String> checkDocumentForValidationErrors(BusinessDocument businessDocument, Errors errors) {
        List<String> errorMessagesList = new ArrayList<>();
        if ((businessDocument.getSeller() == null) || (businessDocument.getCustomer() == null) || (businessDocument.getProducts() == null)) {
            errorMessagesList.add("Некорректная структура документа");
        } else {
            if (errors.hasErrors()) {
                for (ObjectError objectError : errors.getAllErrors()) {
                    boolean errorIsAdded = false;
                    String message = objectError.getDefaultMessage();
                    if (businessDocument.getProducts().length != 0) {
                        if (objectError.getDefaultMessage().equals(PRODUCT_CODE_NOT_CORRECT_MESSAGE)) {
                            String name = getNameOfProductWithError(objectError, businessDocument.getProducts());
                            if (name.isEmpty()) {
                                int number = getNumberOfProductWithError(objectError);
                                message = message.concat(" №").concat(String.valueOf(++number));
                                errorMessagesList.add(message);
                            } else {
                                message = message.concat(" ").concat(name);
                                errorMessagesList.add(message);
                            }
                            errorIsAdded = true;
                        }
                        if (objectError.getDefaultMessage().equals(PRODUCT_NAME_EMPTY_MESSAGE)) {
                            int number = getNumberOfProductWithError(objectError);
                            message = message.concat(String.valueOf(++number));
                        }
                    }
                    if (!errorIsAdded) errorMessagesList.add(message);
                }
            }
            if (businessDocument.getProducts().length == 0) {
                errorMessagesList.add("Список products пуст");
            }
        }
        return errorMessagesList;
    }

    private String getNameOfProductWithError(ObjectError objectError, Product[] products) {
        int number = getNumberOfProductWithError(objectError);
        Product errorProduct = products[number];
        return errorProduct.getName();
    }

    private int getNumberOfProductWithError(ObjectError objectError) {
        String text = objectError.getCodes()[2];
        String value="";
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                value = String.valueOf(text.charAt(i));
                break;
            }
        }
        return Integer.parseInt(value);
    }

}