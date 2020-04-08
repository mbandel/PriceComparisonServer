package com.pc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        if (!categoryService.validate(categoryDto)){
            return new ResponseEntity<>("Category already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Category added", HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<?>getCategories(){
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }
}
