package com.example.restdocumentreciever.service;

import com.example.restdocumentreciever.model.BusinessDocument;
import com.example.restdocumentreciever.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DocumentServiceImpl implements DocumentService {

    private Map<Integer,BusinessDocument> storage = new HashMap<>();
    private int id = 0;

    @Override
    public BusinessDocument saveUpdateDocument(BusinessDocument businessDocument) {
        storage.put(this.id, businessDocument);
        businessDocument.setId(id++);
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

    public Map<Integer, BusinessDocument> getStorage() {
        return new HashMap<>(storage);
    }

}