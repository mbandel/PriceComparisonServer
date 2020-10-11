package com.pc.rating;

import com.pc.poster.Poster;
import com.pc.poster.PosterRepository;
import com.pc.poster.PosterService;
import com.pc.user.User;
import com.pc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    PosterRepository posterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PosterService posterService;

    private static Logger logger = Logger.getLogger(RatingController.class.getName());

    public boolean validate(RatingDto ratingDto){
        Optional<Poster> poster = posterRepository.findById(ratingDto.getPoster().mapToDomain().getId());
        Optional<User> user = userRepository.findById(ratingDto.getUser().mapToDomain().getId());

        if(ratingRepository.existsByUserAndPoster(user.get(), poster.get())){
            return false;
        }

        Rating rating = new Rating(user.get(), ratingDto.getValue(), poster.get());
        ratingRepository.save(rating);

        posterService.editRating(rating, false);
        logger.info("Rating with ID: " + rating.getId() + " added");
        return true;
    }

    public boolean checkRating(Long posterId, Long userId) {
        Poster poster = posterRepository.findById(posterId).get();
        User user = userRepository.findById(userId).get();
        if (!ratingRepository.findByPosterAndUser(poster, user).isPresent()) {
            return false;
        }
        return true;
    }

    public boolean editRating(Long id, Rating ratingUpdate){
        if(ratingRepository.findById(id).isPresent()) {
            Rating rating = ratingRepository.findById(id).get();
            rating.updateValues(ratingUpdate);
            posterService.editRating(rating, true);
            ratingRepository.save(rating);

            return true;
        }
        return false;
    }

}
