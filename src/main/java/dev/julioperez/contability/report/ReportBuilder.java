package dev.julioperez.contability.report;

import dev.julioperez.contability.consorcio.Consorcio;
import dev.julioperez.contability.consorcio.ConsorcioService;
import dev.julioperez.contability.inspector.Inspector;
import dev.julioperez.contability.inspector.InspectorService;
import dev.julioperez.contability.worker.Worker;
import dev.julioperez.contability.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportBuilder {

    private final InspectorService inspectorService;

    private final ConsorcioService consorcioService;

    private final WorkerService workerService;

    @Autowired
    public ReportBuilder(InspectorService inspectorService, ConsorcioService consorcioService, WorkerService workerService) {
        this.inspectorService = inspectorService;
        this.consorcioService = consorcioService;
        this.workerService = workerService;
    }

    public List<ReportDTO> generateReportToAllWorkers(){
        List<ReportDTO> allReportDTO = new ArrayList<>();
        List<Worker> allWorkers = workerService.getAllWorkers();
        for(Worker particularWorker : allWorkers){
            ReportDTO particularReportDTOGenerated = getInformationByWorker(particularWorker);
            allReportDTO.add(particularReportDTOGenerated);
            //addReportDTOInList(allReportDTO, particularReportDTOGenerated);
        }
        return allReportDTO;
    }

    public List<ReportDTO> generateReportByInspectorId(Long idInspector){
        List<ReportDTO> allReportDTO = new ArrayList<>();
        Inspector inspectorToReport = inspectorService.getInspectorById(idInspector);
        List<Consorcio> allConsorciosByInspector = consorcioService.getConsorcioByInspectorId(idInspector);
        for (Consorcio particularConsorcio : allConsorciosByInspector){
            List<Worker> workerByIdConsorcio = workerService.getWorkerByIdConsorcio(particularConsorcio.getId());
            for (Worker particularWorker : workerByIdConsorcio){
                ReportDTO reportDTO = buildReportDTO(particularWorker, particularConsorcio, inspectorToReport);
                addReportDTOInList(allReportDTO, reportDTO);
            }
        }
        return allReportDTO;
    }

    private ReportDTO getInformationByWorker (Worker worker){
        Consorcio consorcio = consorcioService.getConsorcioById(worker.getIdConsorcio());
        Inspector inspector = inspectorService.getInspectorById(consorcio.getIdInspector());

        return buildReportDTO(worker, consorcio, inspector);
    }
    private ReportDTO buildReportDTO(Worker worker, Consorcio consorcio, Inspector inspector){

        return new ReportDTO(
                inspector.getId(),
                consorcio.getId(),
                worker.getId(),
                inspector.getName(),
                inspector.getStartDate(),
                inspector.getPhone(),
                inspector.getEmail(),
                consorcio.getName(),
                consorcio.getCuit(),
                consorcio.getCbu(),
                consorcio.getEmail(),
                "",
                worker.getName(),
                worker.getCuil(),
                worker.getCbu(),
                worker.getPhone(),
                worker.getStartDate(),
                "");

    }
    private List<ReportDTO> addReportDTOInList(List<ReportDTO> reportDTOList, ReportDTO reportDTO){
        reportDTOList.add(reportDTO);
        return reportDTOList;
    }
}
