package com.administration.dto;

import com.administration.entity.Profile;
import lombok.Data;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class FoncResponseDTO {
    private String idFonctionalite;
    private String nomFonc;

    private List<Profile> profiles;
}
