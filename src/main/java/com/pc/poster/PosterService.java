package com.pc.poster;

import com.pc.category.Category;
import com.pc.category.CategoryRepository;
import com.pc.comment.Comment;
import com.pc.product.Product;
import com.pc.product.ProductRepository;
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
        //jezeli poster ma ta sama nazwe oraz ten sam sklep
//        for (Product product: optionalProducts) {
//            if (productRepository.existsByName(product.getName()) && product.getStore().getId()==productDto.getStore().getId()){
//                logger.info("Product : " + productDto.getName() + " already exists");
//                return false;
//            }
//        }
        if (posterRepository.existsByProductAndStore(product.get(), store.get())) {
            logger.info("Poster : " + " already exists");
            return false;
        }
        Poster poster = new Poster(product.get(), posterDto.getPrice(), store.get(), user.get(), posterDto.getDate());
        posterRepository.save(poster);
        logger.info("Poster with ID: " + poster.getId() + " added");
        return true;
    }


    public List<Comment> getComments(Long id){
        return getPosterById(id).getComments();
    }

//    public Poster findByName(String name){
//            Optional<Poster> optionalPoster = posterRepository.findByName(name);
//            if(!optionalPoster.isPresent()){
//                throw new PosterNotFoundException("Poster not found");
//            }
//            return optionalPoster.get();
//    }
}
