package com.pc.product;

import com.pc.category.Category;
import com.pc.category.CategoryRepository;
import com.pc.poster.Poster;
import com.pc.poster.PosterController;
import com.pc.poster.PosterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private static Logger logger = Logger.getLogger(ProductController.class.getName());

    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new PosterNotFoundException("Product  with id: " + productId + " not found");
        }
        logger.info("Product found: " + product.get().getName());
        return product.get();
    }

    public boolean validate(ProductDto productDto){
        if(productRepository.existsByName(productDto.getName())){
            return false;
        }
        Optional<Category> category = categoryRepository.findById(productDto.getCategory().mapToDomain().getId());
        Product product = new Product(productDto.getName(), category.get());
        productRepository.save(product);
        logger.info("Product with ID: " + product.getId() + " added");
        return true;
    }
}
