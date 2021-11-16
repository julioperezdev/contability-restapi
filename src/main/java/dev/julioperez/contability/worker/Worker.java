package dev.julioperez.contability.worker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "worker")
public class Worker {

    @Id
    private Long id;

    private String name;

    private String cuil;

    private String cbu;

    private String phone;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "idconsorcio")
    private Long idConsorcio;

    @Column(name = "idbank")
    private Long idBank;

    public Worker(Long id, String name, String cuil, String cbu, String phone, Date startDate, Long idConsorcio, Long idBank) {
        this.id = id;
        this.name = name;
        this.cuil = cuil;
        this.cbu = cbu;
        this.phone = phone;
        this.startDate = startDate;
        this.idConsorcio = idConsorcio;
        this.idBank = idBank;
    }

    public Worker(String name, String cuil, String cbu, String phone, Date startDate, Long idConsorcio, Long idBank) {
        this.name = name;
        this.cuil = cuil;
        this.cbu = cbu;
        this.phone = phone;
        this.startDate = startDate;
        this.idConsorcio = idConsorcio;
        this.idBank = idBank;
    }
}
