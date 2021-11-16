package dev.julioperez.contability.report;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class ReportDTO {

    private Long idInspector;
    private Long idConsorcio;
    private Long idWorker;
    private String nameInspector;
    private Date startDateInspector;
    private String phoneInspector;
    private String emailInspector;
    private String nameConsorcio;
    private String cuitConsorcio;
    private String cbuConsorcio;
    private String emailConsorcio;
    private String bankConsorcio;
    private String nameWorker;
    private String cuilWorker;
    private String cbuWoker;
    private String phoneWorker;
    private Date startDateWorker;
    private String bankWorker;

    public ReportDTO(Long idInspector, Long idConsorcio, Long idWorker, String nameInspector, Date startDateInspector, String phoneInspector, String emailInspector, String nameConsorcio, String cuitConsorcio, String cbuConsorcio, String emailConsorcio, String bankConsorcio, String nameWorker, String cuilWorker, String cbuWoker, String phoneWorker, Date startDateWorker, String bankWorker) {
        this.idInspector = idInspector;
        this.idConsorcio = idConsorcio;
        this.idWorker = idWorker;
        this.nameInspector = nameInspector;
        this.startDateInspector = startDateInspector;
        this.phoneInspector = phoneInspector;
        this.emailInspector = emailInspector;
        this.nameConsorcio = nameConsorcio;
        this.cuitConsorcio = cuitConsorcio;
        this.cbuConsorcio = cbuConsorcio;
        this.emailConsorcio = emailConsorcio;
        this.bankConsorcio = bankConsorcio;
        this.nameWorker = nameWorker;
        this.cuilWorker = cuilWorker;
        this.cbuWoker = cbuWoker;
        this.phoneWorker = phoneWorker;
        this.startDateWorker = startDateWorker;
        this.bankWorker = bankWorker;
    }
}
