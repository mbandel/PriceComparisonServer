package com.pc.rating;

import com.pc.poster.Poster;
import com.pc.poster.PosterRepository;
import com.pc.poster.PosterService;
import com.pc.user.User;
import com.pc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    RatingService ratingService;
    @Autowired
    PosterRepository posterRepository;
    @Autowired
    UserRepository userRepository;


    @PostMapping("/rating")
    public ResponseEntity<?> addRating(@Valid @RequestBody RatingDto ratingDto){
        if(!ratingService.validate(ratingDto)){
            return new ResponseEntity<>("Rating already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Rating added", HttpStatus.OK);
    }

    @GetMapping("rating/poster/{posterId}/user/{userId}")
    public ResponseEntity<?>getRatingByProduct(@PathVariable Long posterId, @PathVariable Long userId){
        Poster poster = posterRepository.findById(posterId).get();
        User user = userRepository.findById(userId).get();
        if (ratingService.checkRating(posterId, userId)) {
            return new ResponseEntity<>(ratingRepository.findByPosterAndUser(poster, user), HttpStatus.OK);
        }
        return new ResponseEntity<>("Rating doesn't exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("rating/{id}")
    public ResponseEntity<?>editRating(@PathVariable Long id, @Valid @RequestBody Rating rating){
        if (ratingService.editRating(id, rating)){
            return new ResponseEntity<>("Rating edited", HttpStatus.OK);
        }
        return new ResponseEntity<>("Rating not found", HttpStatus.BAD_REQUEST);
    }

}
