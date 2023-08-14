package com.administration.dto;

import com.administration.entity.Ett;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DregionalRequestDTO {
    private String idDr;
    private String dr;
    private String cod_DR;
    private String drAr;
}
