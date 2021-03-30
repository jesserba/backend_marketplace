package com.example.backend_market_place.repository.produit;

import com.example.backend_market_place.models.produit.Categorie;
import com.example.backend_market_place.models.produit.Sous_categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Sous_categorieRepository extends JpaRepository<Sous_categorie, Long> {
    @Query("SELECT u FROM Sous_categorie u WHERE u.categorie = ?1")
    public List<Sous_categorie> findByCategorie(Categorie categorie);

    @Query("DELETE  FROM Sous_categorie u WHERE u.categorie = ?1")
    public void deleteSous_categorieByCategorie(Categorie categorie);


}
