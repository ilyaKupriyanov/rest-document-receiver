package com.example.restdocumentreciever.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @NotBlank(message = "Поле name не должно быть пустым")
    private String name;

    @Size(min = 13, max = 13, message = "Поле code должно содержать 13 символов для товара ")
    private String code;

}