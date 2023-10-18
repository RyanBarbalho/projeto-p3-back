package com.projeto.interact.service.implementation.userServices;

import com.projeto.interact.domain.PostModel;
import com.projeto.interact.domain.ReportModel;
import com.projeto.interact.domain.UserModel;
import com.projeto.interact.respository.PostRepository;
import com.projeto.interact.respository.ReportRepository;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.utils.FindEntityUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserReportService {
    UserRepository userRepository;
    ReportRepository reportRepository;
    PostRepository postRepository;

    public UserReportService(UserRepository userRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    public ReportModel reportPost(Long userId, Long postId, String reason) {
        ReportModel report = new ReportModel();
        report.setReason(reason);
        report.setIdPost(postId);
        report.setUser(FindEntityUtil.findEntityById(userRepository, userId, "user"));

        return reportRepository.save(report);
    }

    //report comment
    public ReportModel reportComment(Long userId, Long commentId, String reason) {
        ReportModel report = new ReportModel();
        report.setReason(reason);
        report.setIdComment(commentId);
        report.setUser(FindEntityUtil.findEntityById(userRepository, userId, "user"));

        return reportRepository.save(report);
    }

    /**
     * Bloqueia um usuário com base em uma denúncia e realiza operações relacionadas.
     * <p>
     *
     * @param reportId O ID do relatório que desencadeou o bloqueio do usuário.
     * @param timeout O tempo de bloqueio a ser aplicado ao usuário.
     * @param reason O motivo pelo qual o usuário está sendo bloqueado.
     * @return O objeto ReportModel atualizado e salvo no banco de dados após o bloqueio do usuário.
     * @throws RuntimeException Se o relatório não puder ser encontrado ou se ocorrer
     *                      uma exceção durante o processamento.
     */

    @Transactional //coloquei parateste
    public ReportModel blockUser(Long reportId, Long timeout, String reason) {
        try{
            ReportModel report = FindEntityUtil.findEntityById(reportRepository, reportId, "report");
            PostModel reportedPost = FindEntityUtil.findEntityById(postRepository, report.getIdPost(), "post");
            UserModel blockedUser = FindEntityUtil.findEntityById(userRepository, reportedPost.getUser().getId(), "user");

            blockedUser.setBlocked(true);

            report.setReason(reason);
            report.setTimeout(timeout);
            report.setAnswered(true);

            //apaga todos os outros reports
            reportRepository.deleteAllByIdPost(report.getIdPost());
            //apaga o post tambem
            postRepository.deleteById(report.getIdPost());

            //salva o report com o timeout e motivo para o usuario ter sido bloqueado
            return reportRepository.save(report);
        }
        catch (Exception e){
            throw new RuntimeException("Report not found");
        }
    }

}
