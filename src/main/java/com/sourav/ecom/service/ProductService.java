package com.sourav.ecom.service;

import com.sourav.ecom.model.Product;
import com.sourav.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getOneProduct(int id) {
        return repository.findById(id).orElse(null);
    }
}
