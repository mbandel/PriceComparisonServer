package com.pc.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentDto commentDto){
        if (!commentService.validate(commentDto)) {
            return new ResponseEntity<>("User already commented this product", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Comment added", HttpStatus.OK);
    }
}
