package com.pc.product;

import com.pc.category.CategoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private CategoryDto category;

    public Product mapToDomain(){
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        if (category!=null){
            product.setCategory(this.category.mapToDomain());
        }

        return product;
    }
}
