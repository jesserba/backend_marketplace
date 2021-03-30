package com.example.backend_market_place.repository.produit;

import com.example.backend_market_place.models.produit.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version, Long> {
}
