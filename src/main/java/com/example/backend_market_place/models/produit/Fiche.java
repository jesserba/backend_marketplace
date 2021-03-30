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
public class Fiche implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_fiche", nullable = false)
    private Long idFiche;
    @Column(name = "destination")
    private String Destination;
    @Column(name = "description")
    private String Description;
    @Column(name = "fragilite")
    private String Fragilite;
    @Column(name = "quantite_disponible")
    private Integer Quantite_disponible;
    @Column(name = "quantite_vendue")
    private Integer Quantite_vendue;
    @Column(name = "garantie")
    private String Garantie;

    @OneToOne
    private Produit produit;


}
