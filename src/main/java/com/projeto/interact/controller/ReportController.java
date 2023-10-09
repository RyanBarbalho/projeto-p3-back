package com.projeto.interact.controller;

import com.projeto.interact.DTO.ReportCommentDTO;
import com.projeto.interact.DTO.ReportPostDTO;
import com.projeto.interact.domain.ReportModel;
import com.projeto.interact.service.implementation.ReportServiceImpl;
import com.projeto.interact.service.implementation.UserReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    ReportServiceImpl reportService;
    UserReportService userReportService;

    public ReportController(ReportServiceImpl reportService, UserReportService userReportService) {
        this.reportService = reportService;
        this.userReportService = userReportService;
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

    //userReportService.
    @PutMapping("/{id}/reportPost")
    public ReportModel reportPost(@RequestBody ReportPostDTO dto, @PathVariable Long id){
        return userReportService.reportPost(dto.userId(), id, dto.reason());
    }


    @PutMapping("/{id}/reportComment")
    public ReportModel reportComment(@RequestBody ReportCommentDTO dto, @PathVariable Long id){
        return userReportService.reportComment(dto.userId(), id, dto.reason());
    }


}
