package com.administration.dto;

import lombok.Data;

import java.util.List;

@Data
public class ModelResponseDTO {
    private String idModel;
    private String desMOD;
    private String obs;
    private List<FoncUpdateDTO> fonctionalites;
    private List<ProfileUpdateDTO> profiles;
}
