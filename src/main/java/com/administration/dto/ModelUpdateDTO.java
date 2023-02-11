package com.administration.dto;

import com.administration.entity.Profile;
import com.administration.entity.Role;
import com.administration.entity.Status;
import lombok.Data;

@Data
public class ModelUpdateDTO {
    private String idModel;
    private String nomModel;
    private boolean consulter;
    private boolean ajouter;
    private boolean modifier;
    private Status status;
    private Role role;

}
