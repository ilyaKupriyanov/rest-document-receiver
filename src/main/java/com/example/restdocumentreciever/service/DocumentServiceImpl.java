package com.example.restdocumentreciever.service;

import com.example.restdocumentreciever.model.BusinessDocument;
import com.example.restdocumentreciever.model.Product;
import org.springframework.stereotype.Component;

@Component
public class DocumentServiceImpl implements DocumentService {

    @Override
    public BusinessDocument saveUpdateDocument(BusinessDocument businessDocument) {
        businessDocument.setId(10);
        return businessDocument;
    }

    @Override
    public BusinessDocument findDocumentById(Integer id) {
        Product[] products2 = {
                new Product("milk", "1113331113331"),
                new Product("water", "3337773337771")
        };

        return new BusinessDocument(1,"seller1", "customer1", products2);
    }

}