package com.example.backend_market_place.models.commande;

import com.example.backend_market_place.models.produit.Produit;
import com.example.backend_market_place.models.utilisateur.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name = "Commande")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_cmd", nullable = false)
    private Long idCmd;
    @Column(name = "date_cmd")
    private Date dateCmd;
    @Column(name = "totale_cmd")
    private Integer totaleCmd;
    @Column(name = "description_cmd")
    private String descriptionCmd;
    @Column(name = "duree_cmd")
    private Date dureeCmd;
    @Column(name = "etat_cmd")
    private String etatCmd;

    @OneToMany(mappedBy = "commande",cascade = CascadeType.PERSIST)
    private List<Quantite> quantites;

    @ManyToOne
    Utilisateur utilisateurs;
}
