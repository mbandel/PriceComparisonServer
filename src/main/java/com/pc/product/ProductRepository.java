package com.pc.product;

import com.pc.comment.Comment;
import com.pc.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);
    boolean existsByName(String name);
    Optional<Product> findByName(String name);
    List<Product>findByOrderByName();
    List<Product>findAllByUser_Id(Long id);
    List<Product>findAllByName(String name);
}
