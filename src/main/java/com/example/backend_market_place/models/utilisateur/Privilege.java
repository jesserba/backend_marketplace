package com.example.backend_market_place.models.utilisateur;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Table(name = "privilege")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_privilege", nullable = false)
    private Long idPrivilege;

    @Enumerated(EnumType.STRING)
    private ERole name;

    @Enumerated(EnumType.STRING)
    private Stat statut;

    @Column(name = "date_creation")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date dateCreation;

    @Column(name = "remarque")
    private String remarque;






}


