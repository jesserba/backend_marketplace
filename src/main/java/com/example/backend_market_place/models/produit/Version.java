package com.example.backend_market_place.models.produit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "Version")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Version implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_version", nullable = false)
    private Long idVersion;
    @Column(name = "nom_version")
    private String nomVersion;
    @Column(name = "dure_de_vie")
    private String Dure_de_vie;
    @Column(name = "technologie")
    private String Technologie;
    @Column(name = "caracteristiques")
    private String Caracteristiques;

    @OneToOne
    private Produit produit;

}
