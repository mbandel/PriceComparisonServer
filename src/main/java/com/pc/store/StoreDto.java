package com.pc.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;

    @Size(max=20)
    private String name;

    @Size(max=80)
    private String address;

    public Store mapToDomain(){
        Store store = new Store();
        store.setId(this.id);
        store.setName(this.name);
        store.setAddress(this.address);
        return store;
    }
}
