package com.example.restdocumentreciever.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class RequestResult {

    String status;
    List<String> errorMessages = new ArrayList<>();

}
