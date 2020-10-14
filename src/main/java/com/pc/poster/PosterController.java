package com.pc.poster;


import com.pc.product.ProductRepository;
import com.pc.product.ProductService;
import com.pc.rating.Rating;
import com.pc.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PosterController {

    @Autowired
    PosterRepository posterRepository;
    @Autowired
    PosterService posterService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/posters/order")
    public ResponseEntity<?> getPostersOrder(){
        return new ResponseEntity<>(posterRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/posters")
    public ResponseEntity<?> getPosters(){
        return new ResponseEntity<>(posterRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/poster/{id}")
    public ResponseEntity<?>getPoster(@PathVariable Long id){
        return new ResponseEntity<>(posterService.getPosterById(id), HttpStatus.OK);
    }

    @GetMapping("poster/user/{id}")
    public ResponseEntity<?>getUserPosters(@PathVariable Long id){
        return new ResponseEntity<>(posterRepository.findAllByUser_Id(id), HttpStatus.OK);
    }

    @GetMapping("posters/product/{id}")
    public ResponseEntity<?> getPostersByProductId(@PathVariable Long id){
        return new ResponseEntity<>(productService.getPostersByProductId(id), HttpStatus.OK);
    }

    @GetMapping("poster/comments/{id}")
    public ResponseEntity<?> getCommentsByPosterId(@PathVariable Long id){
        return new ResponseEntity<>(posterService.getCommentsByPosterId(id), HttpStatus.OK);
    }

    @GetMapping("poster/ratings/{id}")
    public ResponseEntity<?> getRatingsByPosterId(@PathVariable Long id){
        return new ResponseEntity<>(posterService.getRatingsByPosterId(id), HttpStatus.OK);
    }

    @GetMapping("poster/ratingValue/{id}")
    public ResponseEntity<?>getRatingValueByPosterId(@PathVariable Long id){
        int value = 0;
        List<Rating> posterRating = posterService.getRatingsByPosterId(id);
        if (posterRating!=null) {
            for (Rating rating : posterRating) {
                value+=rating.getValue();
            }
        }
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @PostMapping("/poster")
    public ResponseEntity<?> addPoster(@Valid @RequestBody PosterDto posterDto){
        if (!posterService.validate(posterDto)){
            return new ResponseEntity<>("Poster already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Poster added", HttpStatus.OK);
        }
}
