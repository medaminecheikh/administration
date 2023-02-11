package com.administration.dto;

import com.administration.entity.Role;
import com.administration.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelRequestDTO {

    private String nomModel;
    private boolean consulter;
    private boolean ajouter;
    private boolean modifier;
    private Status status;
    private Role role;

}
