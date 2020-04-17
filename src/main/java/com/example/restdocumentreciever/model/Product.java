package com.example.restdocumentreciever.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class Product {

    @NotBlank(message = "Поле name не должно быть пустым")
    private String name;

    @NotBlank(message = "Поле code не должно быть пустым")
    @Size(min = 13, max = 13, message = "Код должен быть равен 13 символам для товара ")
    private String code;

    public Product(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" + name +" : " + code + "}";
    }

}

