package com.example.backend_market_place.repository.utilisateur;

import com.example.backend_market_place.models.utilisateur.ERole;
import com.example.backend_market_place.models.utilisateur.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur>   findByUsernameUtilis (String usernameUtilis);

    Boolean existsByusernameUtilis(String usernameUtilis);

    Boolean existsByemailUtilis(String emailUtilis);

    @Query("SELECT u FROM Utilisateur u WHERE u.verificationCode = ?1")
    public Utilisateur findByVerificationCode(String code);

    @Query("SELECT c FROM Utilisateur c WHERE c.emailUtilis = ?1")
    public Utilisateur findByEmail(String email);

    public Utilisateur findByResetPasswordToken(String token);

    @Query("SELECT u FROM Utilisateur u WHERE u.privilege.name = ?1")
    public List<Utilisateur> findByPrivilegeName(ERole eRole);

    @Query("SELECT u FROM Utilisateur u WHERE u.enabled = ?1")
    public List<Utilisateur> findByEnabled(boolean enabled);

}

