package com.projeto.interact.controller;

import com.projeto.interact.domain.ReportModel;
import com.projeto.interact.service.implementation.ReportServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    ReportServiceImpl reportService;

    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReport(@RequestBody ReportModel report) {
        reportService.create(report);
    }

    @GetMapping("/{id}")
    public ReportModel getReport(@PathVariable long id) {
        return reportService.getReport(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable long id) {
        reportService.deleteReport(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllReports() {
        List<ReportModel> reports = reportService.findAll();
        return ResponseEntity.ok(reports);
    }




}
