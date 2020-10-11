package com.pc.rating;

import com.pc.poster.Poster;
import com.pc.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findById(Long id);
    boolean existsByUserAndPoster(User user, Poster poster);
    Optional<Rating> findByPosterAndUser(Poster poster, User user);

}
