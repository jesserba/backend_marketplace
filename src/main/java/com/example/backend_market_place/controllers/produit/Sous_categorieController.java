package com.example.backend_market_place.controllers.produit;

import com.example.backend_market_place.metier.produit.Sous_categorieInterface;
import com.example.backend_market_place.models.produit.Categorie;
import com.example.backend_market_place.models.produit.Sous_categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Sous_categorie")
public class Sous_categorieController {
    @Autowired
    private Sous_categorieInterface sous_categorieService;

    @GetMapping(value = "/all")
    public List<Sous_categorie> getAllSous_categorie(@Valid@RequestBody Categorie categorie){
        return sous_categorieService.getAllSous_categorie(categorie);
    }
}
