package com.pc.shoppingList;

import com.pc.poster.Poster;
import com.pc.poster.PosterDto;
import com.pc.poster.PosterRepository;
import com.pc.store.Store;
import com.pc.store.StoreRepository;
import com.pc.user.User;
import com.pc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository shoppingListRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PosterRepository posterRepository;

    private static Logger logger = Logger.getLogger(ShoppingListController.class.getName());

    public boolean validate(ShoppingListDto shoppingListDto) {
        Optional<User> user = userRepository.findById(shoppingListDto.getUser().getId());
        Optional<Store> store = storeRepository.findById(shoppingListDto.getStore().getId());
        if (shoppingListRepository.existsByNameAndUser_Id(shoppingListDto.getName(), user.get().getId())){
            logger.info("ShoppingList with this name already exists");
            return false;
        }

        ShoppingList shoppingList = new ShoppingList(shoppingListDto.getName(), shoppingListDto.getDate(), store.get(), user.get());
        shoppingListRepository.save(shoppingList);
        logger.info("Shopping list with id: "+ shoppingList.getId() + " added");
        return true;
    }

    public boolean addPoster(ShoppingListDto shoppingListDto, PosterDto posterDto) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListDto.getId());
        Optional<Poster> poster = posterRepository.findById(posterDto.getId());
        if (shoppingList.isPresent() && poster.isPresent()) {
            shoppingList.get().addPoster(poster.get());
            poster.get().addShoppingList(shoppingList.get());
            posterRepository.save(poster.get());
            shoppingListRepository.save(shoppingList.get());
            return true;
        }
        return false;
    }

    public boolean removePoster(ShoppingListDto shoppingListDto, PosterDto posterDto) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListDto.getId());
        Optional<Poster> poster = posterRepository.findById(posterDto.getId());
        if (shoppingList.isPresent() && poster.isPresent()) {
            shoppingList.get().removePoster(poster.get());
            shoppingListRepository.save(shoppingList.get());
            poster.get().removeShoppingList(shoppingList.get());
            posterRepository.save(poster.get());
            return true;
        }
        return false;
    }

    public List<Poster> findPostersByShoppingList(Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);
        return shoppingList.get().getPosters();
    }

}
