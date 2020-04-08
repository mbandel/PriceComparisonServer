package com.pc.product;

import com.pc.category.Category;
import com.pc.category.CategoryDto;
import com.pc.store.Store;
import com.pc.store.StoreDto;
import com.pc.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductDto {

    private Long id;
    @Size(max = 100)
    private String name;
    private CategoryDto category;
    private double price;
    private StoreDto store;
    private UserDto user;
    private String date;

    public Product mapToDomain(){
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        if (category!=null){
            product.setCategory(this.category.mapToDomain());
        }
        if (store!=null){
            product.setStore(this.store.mapToDomain());
        }
        if (user!=null){
            product.setUser(this.user.mapToDomain());
        }
        product.setPrice(price);
        product.setDate(this.date);
        return product;
    }
    
}
