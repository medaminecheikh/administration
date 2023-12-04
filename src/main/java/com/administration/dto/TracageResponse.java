package com.administration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TracageResponse {
    private Long idTrace;
    private UtilisateurResponseDTO utilisateur;
    private String typeOp;
    private String object;
    private String ip;
    private String browser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC")
    private Date time;
}
