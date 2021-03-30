package com.example.backend_market_place.metier.utilisateur;

import com.example.backend_market_place.models.utilisateur.ERole;
import com.example.backend_market_place.models.utilisateur.Utilisateur;
import jdk.jfr.Enabled;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
public interface UtilisateurInterface {
    List<Utilisateur> getAllUtilisateurs(ERole eRole);

    Utilisateur findByIdUtilisateur(Long id);

    void deleteByIdUtilisateur(Long id);

    void deleteAllUtilisateurs();

    ResponseEntity<Utilisateur> AddUtilisateur(Utilisateur utilisateur, String siteURL) throws UnsupportedEncodingException, MessagingException;

    ResponseEntity<Utilisateur> AddAdmin(Utilisateur utilisateur, String siteURL) throws UnsupportedEncodingException, MessagingException;

    ResponseEntity<Utilisateur> AddConcessionaire(Utilisateur utilisateur, String siteURL) throws UnsupportedEncodingException, MessagingException;

    ResponseEntity<Utilisateur> UpdateUtilisateur(Long id, Utilisateur utilisateur);

    ResponseEntity<?>authenticateUser(Utilisateur utilisateur);

    boolean verify(String verificationCode);

}
