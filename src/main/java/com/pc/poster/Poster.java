package com.pc.poster;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc.category.Category;
import com.pc.comment.Comment;
import com.pc.product.Product;
import com.pc.rating.Rating;
import com.pc.store.Store;
import com.pc.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Poster")
@Getter
@Setter
@NoArgsConstructor
public class Poster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "ProductId")
    private Product product;

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

    @OneToMany(mappedBy = "poster")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "poster")
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @Column
    private int ratingValue;

    public Poster(Product product, Double price, Store store, User user, String date){
        this.product=product;
        this.price=price;
        this.store=store;
        this.user=user;
        this.date=date;
        ratingValue = 0;
    }

    public void deleteRating(int rating){
        this.ratingValue -= rating;
    }

    public void updateRating(int rating){
        this.ratingValue += rating;
    }
}
