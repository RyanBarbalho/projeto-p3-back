package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.ReportModel;
import com.projeto.interact.respository.ReportRepository;
import com.projeto.interact.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.projeto.interact.utils.FindEntityUtil.findEntityById;

@Service
public class ReportServiceImpl implements ReportService {

    //funcao para encontrar qualquer entidade e enviar mensagem de erro em caso de exception

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ReportModel create(ReportModel report) {
        return reportRepository.save(report);
    }

    @Override
    public ReportModel getReport(long id) {
        return findEntityById(reportRepository, id, "report");
    }

    @Override
    public void deleteReport(long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public List<ReportModel> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public List<ReportModel> findAllByBoard(Long id) {
        return reportRepository.findAllByBoardId(id);
    }

}
