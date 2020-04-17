package com.example.restdocumentreciever.service;

import com.example.restdocumentreciever.model.Document;
import com.example.restdocumentreciever.model.Product;
import com.example.restdocumentreciever.model.ValidList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentServiceImpl implements DocumentService {

    @Override
    public Document saveUpdateDocument(Document document) {
        document.setId(10);
        return document;
    }

    @Override
    public Document findDocumentById(Integer id) {
        ValidList<Product> products = new ValidList();
        products.add(new Product("milk", "111333"));
        products.add(new Product("water", "333777"));

        return new Document(1,"seller1", "customer1", products);
    }

}