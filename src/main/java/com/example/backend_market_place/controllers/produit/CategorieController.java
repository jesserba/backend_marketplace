package com.example.backend_market_place.controllers.produit;

import com.example.backend_market_place.metier.produit.CategorieInterface;
import com.example.backend_market_place.models.produit.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Categorie")
public class CategorieController {
    @Autowired
    private CategorieInterface categorieService;
    //api recuperer tout les categorie
    @GetMapping(value = "/all")
    public List<Categorie> getAllCategorie(){
        return categorieService.getAllCategorie();
    }
    //api ajoute categorie
    @PostMapping(value = "/ajout")
    public void ajouterCategorie(@Valid@RequestBody String nom){
        categorieService.addCategorie(nom);
    }
    //api modifier categorie
    @PostMapping(value = "/modifier")
    public void modifierCategorie(@Valid@RequestBody Long id, @Valid @RequestBody String nom){
        categorieService.updateCategorie(id,nom);
    }
}
