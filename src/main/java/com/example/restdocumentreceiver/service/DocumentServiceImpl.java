package com.example.restdocumentreceiver.service;

import com.example.restdocumentreceiver.model.BusinessDocument;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DocumentServiceImpl implements DocumentService {

    private Map<Integer,BusinessDocument> storage = new HashMap<>();
    private int id = 0;

    @Override
    public BusinessDocument saveDocument(BusinessDocument businessDocument) {
        storage.put(this.id, businessDocument);
        businessDocument.setId(id++);
        return businessDocument;
    }

    public Map<Integer, BusinessDocument> getStorage() {
        return new HashMap<>(storage);
    }

}