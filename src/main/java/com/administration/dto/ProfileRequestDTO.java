package com.administration.dto;

import com.administration.entity.Fonctionalite;
import com.administration.entity.Model;
import com.administration.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDTO {

    private String nomProfile;

}
