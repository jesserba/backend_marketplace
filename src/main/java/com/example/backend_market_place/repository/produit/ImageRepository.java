package com.example.backend_market_place.repository.produit;

import com.example.backend_market_place.models.produit.Image;
import com.example.backend_market_place.models.produit.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT u FROM Image u WHERE u.produit = ?1")
    List<Image> findByProduit(Produit produit);
}
