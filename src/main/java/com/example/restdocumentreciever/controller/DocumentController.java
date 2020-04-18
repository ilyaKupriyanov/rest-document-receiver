package com.example.restdocumentreciever.controller;

import com.example.restdocumentreciever.model.BusinessDocument;
import com.example.restdocumentreciever.model.ResultResponse;
import com.example.restdocumentreciever.utils.ValidationErrorsUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class DocumentController {

    @Autowired
    private ValidationErrorsUtils validationErrorsUtils;

    @GetMapping("/")
    public String home() {
        return "Home page";
    }

    @PostMapping(value = "/createDocument", consumes = "application/json", produces = "application/json")
    public ResultResponse createDocument(@Valid @RequestBody BusinessDocument businessDocument, Errors errors) {
        ResultResponse resultResponse;
        List<String> errorMessagesList = validationErrorsUtils.checkDocumentForValidationErrors(businessDocument,errors);
        if (errorMessagesList.isEmpty()) {
            resultResponse = new ResultResponse("SUCCESS", null);
        } else {
            resultResponse = new ResultResponse("VALIDATION_ERROR", errorMessagesList);
        }
        return resultResponse;
    }

}
