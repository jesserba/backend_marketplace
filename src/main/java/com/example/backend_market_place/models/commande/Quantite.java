package com.example.backend_market_place.models.commande;

import com.example.backend_market_place.models.produit.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "Quantite")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quantite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column( name = "idProduit", nullable = false )
    private Long idP;
    @Id
    @Column( name = "idCommande", nullable = false )
    private Long idC;
    @Column(name = "quantite")
    private String quantite;
    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "idProduit", insertable = false, updatable = false )
    private Produit produit;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "idCommande", insertable = false, updatable = false )
    private Commande commande;
}
