package com.example.restdocumentreciever.service;

import com.example.restdocumentreciever.model.Document;

public interface DocumentService {

   Document saveUpdateDocument(Document document);
   Document findDocumentById(Integer id);


}
