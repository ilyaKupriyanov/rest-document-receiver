package com.example.restdocumentreciever.service;

import com.example.restdocumentreciever.model.BusinessDocument;
import com.example.restdocumentreciever.model.Product;
import com.example.restdocumentreciever.model.ValidList;
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
        ValidList<Product> products1 = new ValidList();
        products1.add(new Product("water", "333777"));
        products1.add(new Product("water", "333777"));

        Product[] products2 = {
                new Product("milk", "111333"),
                new Product("water", "333777")
        };
//        ProductsWrapper productsWrapper = new ProductsWrapper();
//        productsWrapper.setProducts(products);
        return new BusinessDocument(1,"seller1", "customer1", products2);
    }

}