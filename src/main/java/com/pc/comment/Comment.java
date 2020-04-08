package com.pc.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc.category.Category;
import com.pc.product.Product;
import com.pc.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "UserId")
    private User user;

    @Column(name="Content")
    private  String content;

    @Column(name = "date")
    private String date;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    @JsonIgnore
    private Product product;

    public Comment(Long id, String content, String date, User user, Product product){
        this.id=id;
        this.content=content;
        this.date=date;
        this.user=user;
        this.product=product;
    }
}
