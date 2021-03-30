package com.example.backend_market_place.repository.produit;

import com.example.backend_market_place.models.produit.Categorie;
import com.example.backend_market_place.models.produit.Produit;
import com.example.backend_market_place.models.produit.Sous_categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query("SELECT u FROM Produit u WHERE u.categorie = ?1")
    public List<Produit> findByCategorie(Categorie categorie);

    @Query("SELECT u FROM Produit u WHERE u.categorie.sous_categories = ?1")
    public  List<Produit> findByCategorieSous_categories(Set<Sous_categorie> sous_categorie);

    @Query("SELECT u FROM Produit u WHERE u.nomProduit = ?1")
    public  List<Produit> findByNomProduit(String nom);

    @Query("SELECT u FROM Produit u WHERE u.prixUnitaire <= ?1 ")
    public List<Produit> findByPrixUnitaire(Long prix);

}
