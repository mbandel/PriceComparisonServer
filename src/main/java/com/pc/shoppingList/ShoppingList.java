package com.pc.shoppingList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc.poster.Poster;
import com.pc.store.Store;
import com.pc.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "Name")
    String name;

    @Column(name = "Date")
    private String date;

    @OneToOne(targetEntity = Store.class)
    @JoinColumn(name = "StoreId")
    private Store store;

    @ManyToMany(mappedBy = "shoppingLists")

    private List<Poster> posters = new ArrayList<>();

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "UserId")
    private User user;

    public ShoppingList(String name, String date, Store store, User user) {
        this.name = name;
        this.date = date;
        this.store = store;
        this.user = user;
    }

    public void addPoster(Poster poster) {
        posters.add(poster);
    }

    public void removePoster(Poster posterToRemove) {
        for (int i=0; i<posters.size(); i++) {
            if (posters.get(i).getId().equals(posterToRemove.getId())){
                posters.remove(i);
            }
        }
    }
}
