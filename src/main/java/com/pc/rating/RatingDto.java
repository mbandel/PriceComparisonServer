package com.pc.rating;

import com.pc.poster.PosterDto;
import com.pc.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {

    private Long id;
    private UserDto user;
    private int value;
    private PosterDto poster;

    public Rating mapToDomain(){
        Rating rating = new Rating();
        rating.setId(this.id);
        if (user!=null){
            rating.setUser(this.user.mapToDomain());
        }
        if(poster!=null){
            rating.setPoster(this.poster.mapToDomain());
        }
        rating.setValue(this.value);

        return rating;
    }


}
