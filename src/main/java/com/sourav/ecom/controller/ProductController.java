package com.sourav.ecom.controller;

import com.sourav.ecom.model.Product;
import com.sourav.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable int id){

        Product product = service.getOneProduct(id);

        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile img){
        try {
            Product pr = service.addProduct(product, img);
            return new ResponseEntity<>(pr, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}/image")
    public ResponseEntity<byte[]> getImgById(@PathVariable int id){
        Product product = service.getOneProduct(id);
        byte[] img = product.getImgData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImgType()))
                .body(img);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product, @RequestPart MultipartFile img){
        Product product1 = null;
        try {
            product1 = service.updateProduct(id, product, img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(product1 != null){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Filed to Update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getOneProduct(id);
        if(product != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }

}
