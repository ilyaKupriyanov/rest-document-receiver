package com.example.restdocumentreciever.service;

import com.example.restdocumentreciever.model.BusinessDocument;

public interface DocumentService {

   BusinessDocument saveUpdateDocument(BusinessDocument businessDocument);
   BusinessDocument findDocumentById(Integer id);


}
