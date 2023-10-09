package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.ReportModel;
import com.projeto.interact.respository.ReportRepository;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.utils.FindEntityUtil;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserReportService {
    UserRepository userRepository;
    ReportRepository reportRepository;

    public UserReportService(UserRepository userRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    public ReportModel reportPost(Long userId, Long postId, String reason) {
        ReportModel report = new ReportModel();
        report.setDescription(reason);
        report.setIdPost(postId);
        report.setUser(FindEntityUtil.findEntityById(userRepository, userId, "user"));

        return reportRepository.save(report);
    }

    //report comment
    public ReportModel reportComment(Long userId, Long commentId, String reason) {
        ReportModel report = new ReportModel();
        report.setDescription(reason);
        report.setIdComment(commentId);
        report.setUser(FindEntityUtil.findEntityById(userRepository, userId, "user"));

        return reportRepository.save(report);
    }

}
