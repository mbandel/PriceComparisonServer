package com.pc.poster;

import com.pc.category.Category;
import com.pc.category.CategoryRepository;
import com.pc.comment.Comment;
import com.pc.product.Product;
import com.pc.product.ProductRepository;
import com.pc.rating.*;
import com.pc.shoppingList.ShoppingList;
import com.pc.shoppingList.ShoppingListDto;
import com.pc.shoppingList.ShoppingListRepository;
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
public class PosterService {
    @Autowired
    PosterRepository posterRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ShoppingListRepository shoppingListRepository;

    private static Logger logger = Logger.getLogger(PosterController.class.getName());

    public Poster getPosterById(Long posterId) {
        Optional<Poster> poster = posterRepository.findById(posterId);
        if (!poster.isPresent()) {
            throw new PosterNotFoundException("Poster with id: " + posterId + " not found");
        }
        logger.info("Poster found: " + poster.get().getProduct().getName());
        return poster.get();
    }

    public boolean validate(PosterDto posterDto) {
        Optional<Product> product = productRepository.findById(posterDto.getProduct().mapToDomain().getId());
        Optional<Store> store = storeRepository.findById(posterDto.getStore().mapToDomain().getId());
        Optional<User> user = userRepository.findById(posterDto.getUser().mapToDomain().getId());

        if (posterRepository.existsByProductAndStore(product.get(), store.get())) {
            logger.info("Poster : " + " already exists");
            return false;
        }
        Poster poster = new Poster(product.get(), posterDto.getPrice(), store.get(), user.get(), posterDto.getDate());
        posterRepository.save(poster);
        logger.info("Poster with ID: " + poster.getId() + " added");
        return true;
    }


    public List<Comment> getCommentsByPosterId(Long id){
        return getPosterById(id).getComments();
    }

    public List<Rating> getRatingsByPosterId(Long id){
        return getPosterById(id).getRatings();
    }

    public Rating getRatingByPosterAndUserId(Long posterId, Long userId){
        List<Rating> ratings = getRatingsByPosterId(posterId);
        for(Rating rating : ratings){
            if (rating.getUser().getId() == userId)
                return rating;
        }
        return null;
    }

    public void editRating(Rating rating, boolean isRatingEdited) {
        Optional<Poster> optionalPoster = posterRepository.findById(rating.getPoster().getId());
        if(!optionalPoster.isPresent()) {
            throw new PosterNotFoundException("Poster not found");
        }
        Poster poster = optionalPoster.get();
        if(isRatingEdited) {
            poster.deleteRating(-1 * rating.getValue());
        }
        poster.updateRating(rating.getValue());
        logger.info("Poster with ID: " + poster.getId() + " updated");
        posterRepository.save(poster);
    }

    public void addPromotion(Long id, PosterDto posterDto) {
        Optional<Poster> optionalPoster = posterRepository.findById(id);
        if(!optionalPoster.isPresent()) {
            throw new PosterNotFoundException("Poster not found");
        }
        Poster poster = optionalPoster.get();
        poster.addPromotion(posterDto);
        posterRepository.save(poster);
    }

}
