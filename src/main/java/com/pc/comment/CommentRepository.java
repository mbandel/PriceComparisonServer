package com.pc.comment;

import com.pc.poster.Poster;
import com.pc.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsById(Long id);
    boolean existsByPosterAndUser(Poster poster, User user);
    
}
