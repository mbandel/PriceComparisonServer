package com.pc.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryDto {
    private Long id;


    private String name;

    public Category mapToDomain(){
        Category category = new Category();
        category.setId(this.id);
        category.setName(category.getName());
        return category;
    }
}
