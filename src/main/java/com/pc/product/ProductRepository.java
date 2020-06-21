package com.pc.product;

import com.pc.poster.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);
    boolean existsByName(String name);
    Optional<Product> findById(Long id);
    List<Product>findAllByCategoryId(Long id);
}
