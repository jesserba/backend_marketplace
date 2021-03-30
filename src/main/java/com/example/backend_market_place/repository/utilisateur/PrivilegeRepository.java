package com.example.backend_market_place.repository.utilisateur;

import com.example.backend_market_place.models.utilisateur.ERole;
import com.example.backend_market_place.models.utilisateur.Privilege;
import com.example.backend_market_place.models.utilisateur.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
