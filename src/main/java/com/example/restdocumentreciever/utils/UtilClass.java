package com.example.restdocumentreciever.utils;

import com.example.restdocumentreciever.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

}
