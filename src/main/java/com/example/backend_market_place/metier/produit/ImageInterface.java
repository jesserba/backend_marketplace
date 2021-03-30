package com.example.backend_market_place.metier.produit;

import com.example.backend_market_place.models.produit.Image;
import com.example.backend_market_place.models.produit.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageInterface {
    Image saveImage (MultipartFile file)throws IOException ;
    List<Image> getImage(Produit produit)throws IOException;
}
