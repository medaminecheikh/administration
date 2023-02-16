package com.administration.dto;

import com.administration.entity.Dregional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneRequestDTO {

    private String desCFRX;
    private String prfxCFRX;
    private String org;
    private String adr;

}
