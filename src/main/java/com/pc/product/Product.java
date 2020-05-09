package com.pc.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc.category.Category;
import com.pc.poster.Poster;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "Name")
    String name;

    @OneToOne(targetEntity = Category.class)
    @JoinColumn(name = "CategoryId")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Poster> posters = new ArrayList<>();

    public Product(String name, Category category){
        this.name=name;
        this.category=category;
    }
}
