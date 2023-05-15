package com.administration.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String username;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
    private int caisse;
    private int f_ADM_CEN;



    // getters and setters

    public int getCaisse() {
        return caisse;
    }

    public void setCaisse(int caisse) {
        this.caisse = caisse;
    }

    public int getF_ADM_CEN() {
        return f_ADM_CEN;
    }

    public void setF_ADM_CEN(int f_ADM_CEN) {
        this.f_ADM_CEN = f_ADM_CEN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}
