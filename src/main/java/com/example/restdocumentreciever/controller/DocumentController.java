package com.example.restdocumentreciever.controller;

import com.example.restdocumentreciever.model.BusinessDocument;
import com.example.restdocumentreciever.model.ResultResponse;
import com.example.restdocumentreciever.service.DocumentService;
import com.example.restdocumentreciever.service.DocumentServiceImpl;
import com.example.restdocumentreciever.utils.ValidationErrorsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
public class DocumentController {

    private DocumentService documentService;
    private ValidationErrorsUtils validationErrorsUtils;

    @Autowired
    public DocumentController(ValidationErrorsUtils validationErrorsUtils, DocumentService documentService) {
        this.validationErrorsUtils = validationErrorsUtils;
        this.documentService = documentService;
    }

    @GetMapping("/")
    public String home() {
        return "Home page";
    }

    @PostMapping(value = "/createDocument", consumes = "application/json", produces = "application/json")
    public ResultResponse createDocument(@Valid @RequestBody BusinessDocument businessDocument, Errors errors) {
        ResultResponse resultResponse;
        List<String> errorMessagesList = validationErrorsUtils.checkDocumentForValidationErrors(businessDocument,errors);
        if (errorMessagesList.isEmpty()) {
            resultResponse = new ResultResponse("SUCCESS", Collections.emptyList());
            documentService.saveUpdateDocument(businessDocument);
        } else {
            Collections.sort(errorMessagesList);
            resultResponse = new ResultResponse("VALIDATION_ERROR", errorMessagesList);
        }
        return resultResponse;
    }

}
