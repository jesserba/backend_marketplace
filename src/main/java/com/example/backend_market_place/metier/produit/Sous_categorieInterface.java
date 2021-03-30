package com.example.backend_market_place.metier.produit;

import com.example.backend_market_place.models.produit.Categorie;
import com.example.backend_market_place.models.produit.Sous_categorie;

import java.util.List;

public interface Sous_categorieInterface {
    List<Sous_categorie> getAllSous_categorie(Categorie categorie);
    void deliteSous_categorie( Categorie categorie);
    void addSous_categorie(Categorie categorie,String nom);
    void updateSous_categorie(Long id, String nom, Categorie categorie);
}
