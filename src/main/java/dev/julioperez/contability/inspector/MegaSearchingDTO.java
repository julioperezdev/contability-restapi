package dev.julioperez.contability.inspector;

import lombok.Data;

@Data
public class MegaSearchingDTO {

    private String email;

    private String phone;




    /*
    * SELECT i.email , i.phone , c.name, w.cuil FROM INSPECTOR i
JOIN CONSORCIO C on i.id = C.idInspector
JOIN WORKER W on C.id = W.idConsorcio
    * */
}
