package com.administration.dto;

import com.administration.entity.Profile;
import com.administration.entity.Role;
import com.administration.entity.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class ModelResponseDTO {
    private String idModel;
    private String nomModel;
    private boolean consulter;
    private boolean ajouter;
    private boolean modifier;
    private Status status;
    private Role role;
    private ProfileUpdateDTO profile;
}
