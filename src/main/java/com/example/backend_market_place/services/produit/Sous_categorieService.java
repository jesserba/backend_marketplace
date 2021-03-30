package com.example.backend_market_place.services.produit;

import com.example.backend_market_place.metier.produit.Sous_categorieInterface;
import com.example.backend_market_place.models.produit.Categorie;
import com.example.backend_market_place.models.produit.Sous_categorie;
import com.example.backend_market_place.repository.produit.Sous_categorieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class Sous_categorieService implements Sous_categorieInterface {
    @Autowired
    Sous_categorieRepository sous_categorieRepository;

    @Override
    public List<Sous_categorie> getAllSous_categorie(Categorie categorie) {
        return sous_categorieRepository.findByCategorie(categorie);
    }

    @Override
    public void deliteSous_categorie(Categorie categorie) {
        sous_categorieRepository.deleteSous_categorieByCategorie(categorie);
    }

    @Override
    public void addSous_categorie(Categorie categorie, String nom) {
     Sous_categorie sous_categorie = new Sous_categorie();
     sous_categorie.setNomSous_categorie(nom);
     sous_categorie.setCategorie(categorie);
     sous_categorieRepository.save(sous_categorie);
    }

    @Override
    public void updateSous_categorie(Long id, String nom, Categorie categorie) {
        Sous_categorie oldSous_categorie = sous_categorieRepository.findById(id).get();
        oldSous_categorie.setNomSous_categorie(nom);
        oldSous_categorie.setCategorie(categorie);
        sous_categorieRepository.save(oldSous_categorie);
    }
}
