package com.example.restdocumentreciever.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {

    private String status;

    private List<String> errorMessages = new ArrayList<>();

}
