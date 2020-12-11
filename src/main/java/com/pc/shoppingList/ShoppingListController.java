package com.pc.shoppingList;

import com.pc.poster.Poster;
import com.pc.poster.PosterDto;
import com.pc.poster.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ShoppingListController {

    @Autowired
    ShoppingListService shoppingListService;
    @Autowired
    ShoppingListRepository shoppingListRepository;
    @Autowired
    PosterRepository posterRepository;

    @GetMapping("shoppingLists")
    public ResponseEntity<?> getAllShoppingLists(){
        return new ResponseEntity<>(shoppingListRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("shoppingList/user/{id}")
    public ResponseEntity<?> getShoppingListByUserId(@PathVariable Long id){
        return new ResponseEntity<>(shoppingListRepository.findAllByUser_Id(id), HttpStatus.OK);
    }

    @GetMapping("shoppingList/{id}/posters")
    public ResponseEntity<?> getPostersByShoppingList(@PathVariable Long id){
        return new ResponseEntity<>(shoppingListService.findPostersByShoppingList(id), HttpStatus.OK);
    }

    @PostMapping("/shoppingList")
    public ResponseEntity<?> addShoppingList(@Valid @RequestBody ShoppingListDto shoppingListDto) {
       if (!shoppingListService.validate(shoppingListDto)) {
           return new ResponseEntity<>("Shopping List already exists", HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>("Shopping List added", HttpStatus.OK);
    }

    @PutMapping("/shoppingList/{id}/addPoster")
    public ResponseEntity<?> addPosterToShoppingList(@PathVariable Long id, @Valid @RequestBody PosterDto posterDto){
        ShoppingListDto shoppingListDto = new ShoppingListDto(id);
        if (shoppingListService.addPoster(shoppingListDto, posterDto)) {
            return new ResponseEntity<>("Poster added to Shopping List", HttpStatus.OK);
        }
        return new ResponseEntity<>("Poster or shopping list doesn't exist", HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/shoppingList/{id}/removePoster")
    public ResponseEntity<?> removePosterFromShoppingList(@PathVariable Long id, @Valid @RequestBody PosterDto posterDto){
        ShoppingListDto shoppingListDto = new ShoppingListDto(id);
        if (shoppingListService.removePoster(shoppingListDto, posterDto)){
            return new ResponseEntity<>("Poster removed from Shopping List", HttpStatus.OK);
        }
        return new ResponseEntity<>("Poster or shopping list doesn't exist", HttpStatus.BAD_REQUEST);

    }


}
