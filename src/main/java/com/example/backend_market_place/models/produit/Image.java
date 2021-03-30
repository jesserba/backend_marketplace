package com.example.backend_market_place.models.produit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Fiche")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Image implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_image", nullable = false)
    private Long idIimage;
    @Column(name = "nom")
    private String nom;
    @Column(name = "type")
    private String Type;
    @Column(name = "taille",length = 1000)
    private byte[] Taille;


    @ManyToOne
    private Produit produit;

}
