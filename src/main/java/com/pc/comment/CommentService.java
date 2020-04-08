package com.pc.comment;

import com.pc.product.Product;
import com.pc.product.ProductRepository;
import com.pc.user.User;
import com.pc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public boolean validate(CommentDto commentDto){
        Optional<User> user =  userRepository.findById(commentDto.getUser().mapToDomain().getId());
        Optional<Product> product = productRepository.findById(commentDto.getProduct().getId());
        Comment comment = new Comment(commentDto.getId(), commentDto.getContent(), commentDto.getDate(), user.get(), product.get());
        commentRepository.save(comment);
        return true;
    }
}
