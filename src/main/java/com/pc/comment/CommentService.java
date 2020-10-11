package com.pc.comment;

import com.pc.poster.Poster;
import com.pc.poster.PosterRepository;
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
    PosterRepository posterRepository;

    public boolean validate(CommentDto commentDto){
        Optional<User> user =  userRepository.findById(commentDto.getUser().mapToDomain().getId());
        Optional<Poster> poster = posterRepository.findById(commentDto.getPoster().getId());
        if (commentRepository.existsByPosterAndUser(poster.get(), user.get())){
            return false;
        }
        Comment comment = new Comment(commentDto.getId(), commentDto.getContent(), commentDto.getDate(), user.get(), poster.get());
        commentRepository.save(comment);
        return true;
    }
}
