package com.projeto.interact.service;

import com.projeto.interact.domain.ReportModel;

import java.util.List;

public interface ReportService {
    ReportModel create(ReportModel report);

    ReportModel getReport(long id);

    void deleteReport(long id);

    //getall
    List<ReportModel> findAll();

    List<ReportModel> findAllByBoard(Long id);
}
