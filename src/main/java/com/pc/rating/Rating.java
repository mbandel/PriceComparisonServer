package com.pc.rating;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc.poster.Poster;
import com.pc.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    @JsonIgnore
    private User user;

    @Column
    private int value;

    @ManyToOne(targetEntity = Poster.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PosterId")
    @JsonIgnore
    private Poster poster;

    public Rating(User user, int value, Poster poster){
        this.user = user;
        this.value = value;
        this.poster = poster;
    }

    public void updateValues(Rating rating){
        this.value = rating.getValue();
    }
}
