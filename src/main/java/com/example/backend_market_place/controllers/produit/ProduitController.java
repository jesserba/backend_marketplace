package com.example.backend_market_place.controllers.produit;


import com.example.backend_market_place.metier.produit.ProduitInterface;
import com.example.backend_market_place.models.produit.*;
import com.example.backend_market_place.services.produit.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    private ProduitInterface produitService;
    //api recuperer tout les produits
    @GetMapping(value = "/all")
    public List<Produit>getAllProduits() throws IOException {
       return produitService.getAllProduits();
    }
    //api recuperer tout les produits selon categorie
    @GetMapping (value = "/Categorie")
    public  List<Produit> getAllProduitCategorie(@Valid @RequestBody Categorie categorie){
        return produitService.getAllProduits(categorie);
    }
    //api recuperer tout les produits selon sous_categorie
    @GetMapping(value = "/Sous_categorie")
    public List<Produit> getAllProduitSous_categorie(@Valid @RequestBody Set<Sous_categorie> sous_categorie){
        return produitService.getAllProduits(sous_categorie);
    }
    //api recuperer tout les produits selon nom
    @GetMapping(value = "/nom")
    public List<Produit> findProduitParNom(@Valid @RequestBody String nom){
        return produitService.findByNomProduit(nom);
    }
    //api recuperer tout les produits selon prix
    @GetMapping(value = "/prix")
    public List<Produit> findProduitParPrix(@Valid @RequestBody Long prix){
        return produitService.findByPrix(prix);
    }
    //api ajouter un produit
    @PostMapping(value = "/ajout")
    public void ajoutProduit(@Valid@RequestBody Produit produit, @Valid@RequestBody Version version, @Valid@RequestBody Fiche fiche, @Valid@RequestBody Categorie categorie,
                             @Valid@RequestBody Set<Sous_categorie> sous_categories, @Valid@RequestBody MultipartFile file) throws IOException {
        produitService.AddProduit(produit,version,fiche,categorie,sous_categories,file);
    }
}
