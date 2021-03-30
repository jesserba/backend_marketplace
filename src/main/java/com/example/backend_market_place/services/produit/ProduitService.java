package com.example.backend_market_place.services.produit;

import com.example.backend_market_place.metier.produit.ImageInterface;
import com.example.backend_market_place.metier.produit.ProduitInterface;
import com.example.backend_market_place.models.produit.*;
import com.example.backend_market_place.repository.produit.FicheRepository;
import com.example.backend_market_place.repository.produit.ImageRepository;
import com.example.backend_market_place.repository.produit.ProduitRepository;
import com.example.backend_market_place.repository.produit.VersionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ProduitService implements ProduitInterface {
    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    FicheRepository ficheRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;


    @Override
    public List<Produit> getAllProduits() throws IOException {
        List<Produit> produits = produitRepository.findAll();
        for(int i=0; i< produits.size();i++)
        {
            produits.get(i).setImages(imageService.getImage(produits.get(i)));
        }
        return produits;
    }

    @Override
    public List<Produit> getAllProduits(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> getAllProduits(Set<Sous_categorie> sous_categorie){
        return produitRepository.findByCategorieSous_categories(sous_categorie);
    }

    @Override
    public Produit findByIdProduit(Long id) {
        return produitRepository.findById(id).get();
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByPrix(Long prix) {
        return produitRepository.findByPrixUnitaire(prix);
    }

    @Override
    public void AddProduit(Produit produit, Version version, Fiche fiche, Categorie categorie, Set<Sous_categorie> sous_categorie , MultipartFile file) throws IOException {
        versionRepository.save(version);
        ficheRepository.save(fiche);
        produit.setVersion(version);
        produit.setFiche(fiche);
        produit.setCategorie(categorie);
        produit.getCategorie().setSous_categories(sous_categorie);
        List<Image> img = null;
        img.add(imageService.saveImage(file));
        produit.setImages(img);
        produitRepository.save(produit);
    }



    @Override
    public void deleteByIdProduit(Long id) {
        Produit produit = findByIdProduit(id);
        Version version =  produit.getVersion();
        Fiche fiche =  produit.getFiche();
        List<Image> image = produit.getImages();
        versionRepository.delete(version);
        ficheRepository.delete(fiche);
        imageRepository.deleteAll(image);
        produitRepository.delete(produit);
    }

    @Override
    public void deleteAllProduit(Sous_categorie sous_categorie) {


    }

    @Override
    public void updateProduir(Produit produit, Version version, Fiche fiche, Categorie categorie, Sous_categorie sous_categorie) {

    }


}
