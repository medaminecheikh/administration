package com.administration.dto;

import com.administration.entity.Dregional;
import com.administration.entity.Ett;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class ZoneResponseDTO {
    private String codZone ;
    private String codEtt;
    private String desCFRX;
    private String prfxCFRX;
    private String org;
    private String adr;
    private List<DregionalResponseDTO> dregionals;
    private  List<EttUpdateDTO> etts;
}
