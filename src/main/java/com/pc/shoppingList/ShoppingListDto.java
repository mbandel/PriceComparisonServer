package com.pc.shoppingList;

import com.pc.store.StoreDto;
import com.pc.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDto {

    private Long id;
    private String name;
    private String date;
    private StoreDto store;
    private UserDto user;

    public ShoppingList mapToDomain() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(this.id);
        shoppingList.setName(this.name);
        shoppingList.setDate(this.date);
        if (store != null) {
            shoppingList.setStore(this.store.mapToDomain());
        }
        if (user != null) {
            shoppingList.setUser(this.user.mapToDomain());
        }
        return shoppingList;
    }

    public ShoppingListDto(Long id){
        this.id = id;
    }

}
