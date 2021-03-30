package com.example.backend_market_place.metier.produit;

import com.example.backend_market_place.models.produit.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProduitInterface {
    List<Produit> getAllProduits() throws IOException;
    List<Produit> getAllProduits(Categorie categorie);
    List<Produit> getAllProduits(Set<Sous_categorie> sous_categorie);
    void AddProduit(Produit produit, Version version, Fiche fiche,
                    Categorie categorie, Set<Sous_categorie> sous_categorie, MultipartFile file) throws IOException;
    List<Produit> findByNomProduit(String nom);
    List<Produit>findByPrix(Long prix);
    Produit findByIdProduit(Long id);
    void deleteByIdProduit(Long id);
    void deleteAllProduit(Sous_categorie sous_categorie);
    void updateProduir(Produit produit, Version version, Fiche fiche,
                       Categorie categorie, Sous_categorie sous_categorie);
}
