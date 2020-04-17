package com.example.restdocumentreciever.controller;

import com.example.restdocumentreciever.model.BusinessDocument;
import com.example.restdocumentreciever.model.ResultResponse;
import com.example.restdocumentreciever.service.DocumentService;
import com.example.restdocumentreciever.utils.UtilClass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UtilClass utilClass;

    @GetMapping("/")
    public String home() {
        return "Spring boot is working!";
    }

    @PostMapping(value = "/createDocument", consumes = "application/json", produces = "application/json")
    public ResultResponse createDocument(@Valid @RequestBody BusinessDocument businessDocument, Errors errors) {

        List<String> errorMessagesList = new ArrayList<>();
        ResultResponse resultResponse;
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                String message = objectError.getDefaultMessage();
                if (businessDocument.getProducts().length != 0) {
                    if (objectError.getCodes()[0].contains("products")) {
                        String name = utilClass.getNameOfProductWithError(objectError, businessDocument.getProducts());
                        message = message.concat(name);
                    }
                }
                errorMessagesList.add(message);
            }
        }
        if (errorMessagesList.isEmpty()) {
            resultResponse = new ResultResponse("SUCCESS", null);
        } else {
            resultResponse = new ResultResponse("VALIDATION_ERROR", errorMessagesList);
        }
        return resultResponse;
    }

}
