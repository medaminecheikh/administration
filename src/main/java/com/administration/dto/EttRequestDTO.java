package com.administration.dto;

import com.administration.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EttRequestDTO {

    private String address;
    private  int tel;
    private  String email;
    private boolean disponibilite;

}
