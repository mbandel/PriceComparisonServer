package com.pc.poster;

import com.pc.category.CategoryDto;
import com.pc.product.ProductDto;
import com.pc.store.StoreDto;
import com.pc.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class PosterDto {

    private Long id;
    private ProductDto product;
    private double price;
    private StoreDto store;
    private UserDto user;
    private String date;

    public Poster mapToDomain(){
        Poster poster = new Poster();
        poster.setId(this.id);
        if(product!=null){
            poster.setProduct(this.product.mapToDomain());
        }
        if (store!=null){
            poster.setStore(this.store.mapToDomain());
        }
        if (user!=null){
            poster.setUser(this.user.mapToDomain());
        }
        poster.setPrice(price);
        poster.setDate(this.date);
        return poster;
    }
    
}
