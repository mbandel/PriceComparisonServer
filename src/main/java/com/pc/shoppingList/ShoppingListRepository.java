package com.pc.shoppingList;

import com.pc.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    boolean existsById(Long id);
    boolean existsByNameAndUser_Id(String name, Long id);
    List<ShoppingList> findAllByUser_Id(Long id);
}
