package dev.julioperez.contability.report;

import dev.julioperez.contability.shared.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportBuilder reportBuilder;

    @Autowired
    public ReportController(ReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }

    @GetMapping
    public RestResponse<List<ReportDTO>> getAllReportDTO(){
        List<ReportDTO> reportDTOS = reportBuilder.generateReportToAllWorkers();
        return new RestResponse<>(HttpStatus.FOUND, "Todos los reports", reportDTOS);
    }
}
