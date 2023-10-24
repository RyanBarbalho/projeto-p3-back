package com.projeto.interact.service.implementation.userServices;

import com.projeto.interact.domain.comment.CommentModel;
import com.projeto.interact.domain.post.PostModel;
import com.projeto.interact.domain.ReportModel;
import com.projeto.interact.domain.user.UserModel;
import com.projeto.interact.respository.CommentRepository;
import com.projeto.interact.respository.PostRepository;
import com.projeto.interact.respository.ReportRepository;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.utils.FindEntityUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserReportService {
    UserRepository userRepository;
    ReportRepository reportRepository;
    PostRepository postRepository;
    CommentRepository commentRepository;

    public UserReportService(UserRepository userRepository, ReportRepository reportRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;

    }

    public ReportModel reportPost(Long userId, Long postId, String reason, Long boardId) {
        ReportModel report = new ReportModel();
        report.setReason(reason);
        report.setIdPost(postId);
        report.setBoardId(boardId);
        report.setUser(FindEntityUtil.findEntityById(userRepository, userId, "user"));

        return reportRepository.save(report);
    }

    //report comment
    public ReportModel reportComment(Long userId, Long commentId, String reason, Long boardId) {
        ReportModel report = new ReportModel();
        report.setReason(reason);
        report.setIdComment(commentId);
        report.setBoardId(boardId);
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

            //verifica se é post ou comentario
            //bloqueia o usuario e deleta post ou comentario.
            if(report.getIdPost() != null){
                PostModel post = FindEntityUtil.findEntityById(postRepository, report.getIdPost(), "post");
                UserModel user = FindEntityUtil.findEntityById(userRepository, post.getUser().getId(), "user");
                user.setBlocked(true);
                user.setBlockedUntil(LocalDateTime.now().plus(Duration.ofDays(timeout)));
                userRepository.save(user);
                post.setTitle("Essa questão foi removida!");
                post.setText("Essa questão foi removida!");
                postRepository.save(post);
            }
            else{
                CommentModel comment = FindEntityUtil.findEntityById(commentRepository, report.getIdComment(), "comment");
                UserModel user = FindEntityUtil.findEntityById(userRepository, report.getUser().getId(), "user");
                user.setBlocked(true);
                userRepository.save(user);
                comment.setText("Esta resposta foi removida!");
                commentRepository.save(comment);
            }

            report.setReason(reason);
            report.setTimeout(timeout);
            report.setAnswered(true);

            //salva o report com o timeout e motivo para o usuario ter sido bloqueado
            return reportRepository.save(report);
        }
        catch (Exception e){
            throw new RuntimeException("Report not found");
        }
    }

}
