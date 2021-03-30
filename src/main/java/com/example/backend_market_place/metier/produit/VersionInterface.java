package com.example.backend_market_place.metier.produit;

import com.example.backend_market_place.models.produit.Version;

public interface VersionInterface {
    void updateVersion(Version version,Long id);
}
