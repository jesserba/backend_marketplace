package com.example.backend_market_place.security.Jwt;



public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;

    private String privileges;

    public JwtResponse(String accessToken, Long id,String privileges) {
        this.token = accessToken;
        this.id = id;

        this.privileges = privileges;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }
}
