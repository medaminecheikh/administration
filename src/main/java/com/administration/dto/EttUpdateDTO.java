package com.administration.dto;

import com.administration.entity.Ett;
import lombok.Data;

import java.util.List;

@Data
public class EttUpdateDTO {
    private String idEtt;
    private String address;
    private  int tel;
    private  String email;
    private boolean disponibilite;
}
