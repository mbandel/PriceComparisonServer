package com.pc.product;

import com.pc.poster.PosterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto){
        if (!productService.validate(productDto)){
            return new ResponseEntity<>("Product already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Product added", HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
        public ResponseEntity<?>getProductById(@PathVariable Long id){
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }

    @GetMapping("/products/category/{id}")
    public ResponseEntity<?>getPostersByProductName(@PathVariable Long id){
        return new ResponseEntity<>(productRepository.findAllByCategoryId(id), HttpStatus.OK);
    }



}
