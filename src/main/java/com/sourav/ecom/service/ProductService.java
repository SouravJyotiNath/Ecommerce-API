package com.sourav.ecom.service;

import com.sourav.ecom.model.Product;
import com.sourav.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Product addProduct(Product product, MultipartFile img) throws IOException {
        product.setImgName(img.getOriginalFilename());
        product.setImgType(img.getContentType());
        product.setImgData(img.getBytes());
        return repository.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile img) throws IOException {
        product.setImgData(img.getBytes());
        return repository.save(product);
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }

    public List<Product> searchProduct(String key) {
        return repository.searchProduct(key);
    }
}
