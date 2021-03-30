package com.example.backend_market_place.repository.produit;

import com.example.backend_market_place.models.produit.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
