package dev.julioperez.contability.consorcio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "consorcio")
public class Consorcio {

    @Id
    private Long id;

    private String name;

    private String cuit;

    private String cbu;

    private String email;

    @Column(name = "idinspector")
    private Long idInspector;

    @Column(name = "idbank")
    private int idBank;

    public Consorcio(Long id, String name, String cuit, String cbu, String email, Long idInspector, int idBank) {
        this.id = id;
        this.name = name;
        this.cuit = cuit;
        this.cbu = cbu;
        this.email = email;
        this.idInspector = idInspector;
        this.idBank = idBank;
    }

    public Consorcio(String name, String cuit, String cbu, String email, Long idInspector, int idBank) {
        this.name = name;
        this.cuit = cuit;
        this.cbu = cbu;
        this.email = email;
        this.idInspector = idInspector;
        this.idBank = idBank;
    }
}
