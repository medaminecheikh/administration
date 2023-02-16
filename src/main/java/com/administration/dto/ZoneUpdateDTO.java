package com.administration.dto;

import com.administration.entity.Dregional;
import lombok.Data;

import java.util.List;

@Data
public class ZoneUpdateDTO {
    private String codZone ;
    private String codEtt;
    private String desCFRX;
    private String prfxCFRX;
    private String org;
    private String adr;
}
