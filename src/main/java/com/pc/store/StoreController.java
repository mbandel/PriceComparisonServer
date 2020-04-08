package com.pc.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class StoreController {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreService storeService;

    @GetMapping("/stores")
    public ResponseEntity<?> getStores(){
        return new ResponseEntity<>(storeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<?> addStore(@Valid @RequestBody StoreDto storeDto){
        if (!storeService.validate(storeDto)){
            return new ResponseEntity<>("Store already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Store added", HttpStatus.OK);
    }

}
