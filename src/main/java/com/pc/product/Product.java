package com.pc.product;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc.category.Category;
import com.pc.comment.Comment;
import com.pc.store.Store;
import com.pc.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToOne(targetEntity = Category.class)
    @JoinColumn(name = "CategoryId")
    private Category category;

    @Column(name = "Price")
    private Double price;

    @OneToOne(targetEntity = Store.class)
    @JoinColumn(name = "StoreId")
    private Store store;

    @Column(name = "Date")
    private String date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "UserId")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();


    public Product(String name, Category category, Double price, Store store, User user, String date){
        this.name=name;
        this.category=category;
        this.price=price;
        this.store=store;
        this.user=user;
        this.date=date;
    }

}
