package com.administration.service;

import com.administration.dto.TracageResponse;
import com.administration.entity.Tracage;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ITracageService {
    List<TracageResponse> getTraceByAll(String idUser, Date  time, String ip, String browser, String op);
    List<Tracage> getTraceByPage(String idUser, Timestamp time, String ip, String browser, String op, Pageable pageable);

    void addTrace(Tracage tracage);

    List<TracageResponse> getByencaissement();

    List<TracageResponse> getByfacture();

    List<TracageResponse> getBycaisse();
}
