package com.administration.dto;

import com.administration.entity.Ett;
import lombok.Data;

import java.util.List;

@Data
public class EttUpdateDTO {
    private String codEtt;
    private String des_SRC_ENC;
    private String prfx_SRC_ENC;
    private String adr;
    private int is_BSCS;
}
