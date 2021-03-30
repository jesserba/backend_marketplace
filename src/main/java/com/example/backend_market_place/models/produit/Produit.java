package com.example.backend_market_place.models.produit;


import com.example.backend_market_place.models.commande.Quantite;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Table(name = "Produit")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_produit", nullable = false)
    private Long idProduit;
    @Column(name = "nom_produit")
    private String nomProduit;
    @Column(name = "prix_unitaire")
    private String prixUnitaire;
    @Column(name = "reference")
    private String reference;
    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "date_ajout")
    private Date date_ajout;
    @Column(name = "origine")
    private String origine;
    @Column(name = "marque")
    private String marque;
    @Column(name = "couleur")
    private String  couleur;

    @ManyToOne
    Categorie categorie;

    @OneToOne(mappedBy = "produit")
    private Fiche fiche;

    @OneToOne(mappedBy = "produit")
    private Version version;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
    private List<Image> images;

    @OneToMany(mappedBy = "produit",cascade = CascadeType.PERSIST)
    private List<Quantite> quantites;



}
