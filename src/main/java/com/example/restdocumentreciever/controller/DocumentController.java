package com.example.restdocumentreciever.controller;

import com.example.restdocumentreciever.model.Document;
import com.example.restdocumentreciever.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/")
    public String home() {
        return "Spring boot is working!";
    }

    @PostMapping(value = "/createDocument", consumes = "application/json", produces = "application/json")
    public String createDocument(@Valid @RequestBody Document document, Errors errors) {
        //ResponseEntity<Void>
        //documentService.saveUpdateDocument(document);
        if (errors.hasErrors()) {
            return errors.getAllErrors().toString();
        } else {
            int id = documentService.saveUpdateDocument(document).getId();
            return "Document "+ String.valueOf(id) + "SUCCESS Inserted!";
        }
//        return ResponseEntity.ok().build();
    }


}
