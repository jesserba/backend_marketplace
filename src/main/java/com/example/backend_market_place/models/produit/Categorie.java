package com.example.backend_market_place.models.produit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "Categorie")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_categorie", nullable = false)
    private Long idCategorie;
    @Column(name = "nom_categorie")
    private String nomCategorie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie")
    private Set<Produit> produits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie")
    private Set<Sous_categorie> sous_categories;
}
