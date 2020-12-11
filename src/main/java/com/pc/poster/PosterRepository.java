package com.pc.poster;

import com.pc.product.Product;
import com.pc.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Long> {
    boolean existsById(Long id);
    boolean existsByProductAndStore(Product product, Store store);
    List<Poster>findAllByUser_Id(Long id);
}
