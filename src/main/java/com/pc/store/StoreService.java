package com.pc.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;
    private static Logger logger = Logger.getLogger(StoreController.class.getName());

    public boolean validate (StoreDto storeDto){
        if (storeRepository.existsByName(storeDto.getName()) && storeRepository.existsByAddress(storeDto.getAddress())){
            return false;
        }
        Store store = new Store(storeDto.getId(), storeDto.getName(), storeDto.getAddress());
        storeRepository.save(store);
        logger.info("Store with id: " + store.getId() + " added");
        return true;
    }
}
