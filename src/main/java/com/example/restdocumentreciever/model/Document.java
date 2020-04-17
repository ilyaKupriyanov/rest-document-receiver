package com.example.restdocumentreciever.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Document {

    private int id;

    @NotBlank(message = "seller не должно быть пустым")
    @Size(min = 9, max = 9, message = "в seller должно быть 9 символов")
    private String seller;

    @NotBlank(message = "customer не должно быть пустым")
    @Size(min = 9, max = 9, message = "в customer должно быть 9 символов" )
    private String customer;

    @NotEmpty
    private ValidList<Product> products;

}
