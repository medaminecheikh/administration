package com.administration.dto;

import com.administration.entity.Ett;
import lombok.Data;

import java.util.List;

@Data
public class DregionalUpdateDTO {
    private String idDregional;
    private String address;
    private int tel;
    private String email;
    private String codePostal;
    private String chefDirection;

}
