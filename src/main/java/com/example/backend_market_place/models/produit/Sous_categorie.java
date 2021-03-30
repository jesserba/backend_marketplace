package com.example.backend_market_place.models.produit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Sous_categorie")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Sous_categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_sous_categorie", nullable = false)
    private Long idSous_categorie;
    @Column(name = "nom_sous_categorie")
    private String nomSous_categorie;

    @ManyToOne
    Categorie categorie;
}
