package com.example.backend_market_place.metier.produit;

import com.example.backend_market_place.models.produit.Categorie;

import java.util.List;

public interface CategorieInterface {
    List<Categorie> getAllCategorie();
    void deliteAllCategorie();
    void addCategorie(String nom);
    void updateCategorie(Long id,String nom);
}
