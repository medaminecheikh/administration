package com.administration.dto;

import com.administration.entity.Ett;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class DregionalUpdateDTO {
    private String idDr;
    private String cod_DR;
    private String dr;
    private String drAr;

}
