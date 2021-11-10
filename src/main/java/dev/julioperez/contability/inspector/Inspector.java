package dev.julioperez.contability.inspector;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Inspector")
public class Inspector {

    @Id
    private Long id;

    private String name;

    @Column(name = "startdate")
    private Date startDate;

    private String phone;

    private String email;

    public Inspector(Long id, String name, Date startDate, String phone, String email) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.phone = phone;
        this.email = email;
    }

    public Inspector(String name, Date startDate, String phone, String email) {
        this.name = name;
        this.startDate = startDate;
        this.phone = phone;
        this.email = email;
    }
}
