package com.pc.store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsById(Long id);
    boolean existsByName(String name);
    boolean existsByAddress(String address);
}
