package com.example.backend_market_place.repository.produit;

import com.example.backend_market_place.models.produit.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheRepository extends JpaRepository<Fiche, Long> {
}
