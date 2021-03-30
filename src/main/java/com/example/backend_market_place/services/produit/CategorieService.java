package com.example.backend_market_place.services.produit;

import com.example.backend_market_place.metier.produit.CategorieInterface;
import com.example.backend_market_place.models.produit.Categorie;
import com.example.backend_market_place.repository.produit.CategorieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategorieService implements CategorieInterface {
@Autowired
    CategorieRepository categorieRepository;


    //recuperer tout les categories
    @Override
    public List<Categorie> getAllCategorie(){
        return categorieRepository.findAll();
    }
    @Override
    public void deliteAllCategorie(){
        categorieRepository.deleteAll();
    }

    @Override
    public void addCategorie(String nom) {
        Categorie categorie = new Categorie();
        categorie.setNomCategorie(nom);
        categorieRepository.save(categorie);
    }

    @Override
    public void updateCategorie(Long id,String nom) {
        Categorie oldcategorie = categorieRepository.findById(id).get();
        oldcategorie.setNomCategorie(nom);
        categorieRepository.save(oldcategorie);
    }


}
