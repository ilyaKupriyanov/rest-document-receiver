package com.example.restdocumentreciever.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDocument {

    private int id;

    @NotBlank(message = "Поле seller не должно быть пустым")
    @Size(min = 9, max = 9, message = "В поле seller должно быть 9 символов")
    private String seller;

    @NotBlank(message = "Поле customer не должно быть пустым")
    @Size(min = 9, max = 9, message = "В поле customer должно быть 9 символов" )
    private String customer;

    @Valid
    private Product[] products;

}
