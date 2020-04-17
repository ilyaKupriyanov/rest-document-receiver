package com.example.restdocumentreciever.utils;

import com.example.restdocumentreciever.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UtilClass {

    public List<String> validateProductList(List<Product> products){
        List<String> productsErrorList = new ArrayList<>();
        if (products.isEmpty()) {
            productsErrorList.add("Список товаров пустой!");
            return productsErrorList;
        }
        for (Product product : products) {
            if (!(product.getCode().length() == 13)) {
                productsErrorList.add("Продукт с именем " + product.getName() + "имеет некорректный код (длина должна быть 13 символов");
            }
        }
        return productsErrorList;
    }

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


