package com.example.backend_market_place.models.utilisateur;

import com.example.backend_market_place.models.commande.Commande;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;


@Table(name = "utilisateur")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable , UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id_utilis", nullable = false)
    private Long idUtilis;
    @Column(name = "nom_utilis")
    private String nomUtilis;
    @Column(name = "prenom_utilis")
    private String prenomUtilis;
    @Column(name = "email_utilis")
    private String emailUtilis;
    @Column(name = "tel_utilis")
    private String telUtilis;
    @Column(name = "code_postal")
    private String codePostal;
    @Column(name = "ville")
    private String ville;
    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "date_de_naissance")
    private Date DNUtilis;
    @Column(name = "image_Utilis")
    private String imageUtilis;
    @Column(name = "genre_Utilis")
    private String genreUtilis;
    @Column(name = "N_rue_Utilis")
    private String NRueUtilis;
    @Column(name = "username_utilis")
    private String usernameUtilis;
    @Column(name = "pwd_utilis")
    private String pwdUtilis;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    private boolean enabled;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @ManyToOne(cascade = CascadeType.ALL)
    Privilege privilege;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateurs")
    private Set<Commande> commandes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList <GrantedAuthority> ();
        authorities.add(new SimpleGrantedAuthority( getPrivilege().getName().name()));
        return authorities;
    }

    public String getFullName() {
        String fullname = nomUtilis + prenomUtilis;
        return fullname;
    }

    @Override
    public String getPassword() {
        return pwdUtilis;
    }

    @Override
    public String getUsername() {
        return usernameUtilis;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Utilisateur user = (Utilisateur) o;
        return Objects.equals(idUtilis, user.idUtilis);
    }
}

