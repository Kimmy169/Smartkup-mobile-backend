package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    // Allows the app to fetch only the shops created by the logged-in user
    List<Shop> findByUserId(Long userId);
}