package com.pc.comment;

import com.pc.poster.PosterDto;
import com.pc.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentDto {

    private Long id;
    @Size(max = 300)
    private String content;
    private UserDto user;
    private PosterDto product;
    private String date;

    public Comment mapToDomain(){
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setContent(this.content);
        if (comment.getUser()!=null){
            comment.setUser(this.user.mapToDomain());
        }
        if(comment.getPoster()!=null){
            comment.setPoster(this.product.mapToDomain());
        }
        comment.setDate(this.date);
        return comment;
    }
}
