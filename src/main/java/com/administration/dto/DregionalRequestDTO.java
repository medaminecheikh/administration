package com.administration.dto;

import com.administration.entity.Ett;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DregionalRequestDTO {
    private String address;
    private int tel;
    private String email;
    private String codePostal;
    private String chefDirection;

}
