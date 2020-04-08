package com.pc.category;

import com.pc.product.Product;
import com.pc.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    private static Logger logger = Logger.getLogger(CategoryController.class.getName());

    public boolean validate(CategoryDto categoryDto){
        if (categoryRepository.existsByName(categoryDto.getName())){
            logger.info("Category " + categoryDto.getName() + "already exists");
            return false;
        }
        Category category = new Category(categoryDto.getName());
        categoryRepository.save(category);
        logger.info("Category with ID: " + category.getId() + "added");
        return true;
    }


}
