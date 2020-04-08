package com.pc.product;


import com.pc.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    StoreRepository storeRepository;

    @GetMapping("/products/order")
    public ResponseEntity<?> getProductsOrder(){
        return new ResponseEntity<>(productRepository.findByOrderByName(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?>getProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("product/user/{id}")
    public ResponseEntity<?>getUserProducts(@PathVariable Long id){
        return new ResponseEntity<>(productRepository.findAllByUser_Id(id), HttpStatus.OK);
    }

    @GetMapping("products/{name}")
    public ResponseEntity<?> getStoresByProductName(@PathVariable String name){
        return new ResponseEntity<>(productRepository.findAllByName(name), HttpStatus.OK);
    }

    @GetMapping("product/comments/{id}")
    public ResponseEntity<?> getCommentsByProductId(@PathVariable Long id){
        return new ResponseEntity<>(productService.getComments(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto){
        if (!productService.validate(productDto)){
            return new ResponseEntity<>("Product already exist", HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>("Product added", HttpStatus.OK);
        }
}
