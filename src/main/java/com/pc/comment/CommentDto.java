package com.pc.comment;

import com.pc.product.ProductDto;
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
    private ProductDto product;
    private String date;

    public Comment mapToDomain(){
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setContent(this.content);
        if (comment.getUser()!=null){
            comment.setUser(this.user.mapToDomain());
        }
        if(comment.getProduct()!=null){
            comment.setProduct(this.product.mapToDomain());
        }
        comment.setDate(this.date);
        return comment;
    }
}
