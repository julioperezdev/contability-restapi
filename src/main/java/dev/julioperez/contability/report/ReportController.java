package dev.julioperez.contability.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportBuilder reportBuilder;

    @Autowired
    public ReportController(ReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }
}
