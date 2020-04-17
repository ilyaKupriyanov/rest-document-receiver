package com.example.restdocumentreciever.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class Product {
    private String name;

    @NotBlank
    @Size(min = 13, max = 13)
    private String code;

    public Product(String name, String code) {
        this.name = name;
        this.code = code;
    }

}

