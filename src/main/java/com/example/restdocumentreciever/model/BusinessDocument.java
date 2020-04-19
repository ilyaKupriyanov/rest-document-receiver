package com.example.restdocumentreciever.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDocument {

    private int id;

    @Size(min = 9, max = 9, message = "Поле seller должно содержать 9 символов")
    private String seller;

    @Size(min = 9, max = 9, message = "Поле customer должно содержать 9 символов" )
    private String customer;

    @Valid
    private Product[] products;

}