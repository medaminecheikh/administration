package com.administration.dto;

import com.administration.entity.Ett;
import com.administration.entity.Zone;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class DregionalResponseDTO {
    private String idDr;
    private String cod_DR;
    private String dr;

    private String drAr;

    private ZoneUpdateDTO zone;

    private List<EttUpdateDTO> etts;
    private long totalElements;
}
