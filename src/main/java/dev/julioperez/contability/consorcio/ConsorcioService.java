package dev.julioperez.contability.consorcio;

import java.util.List;
import java.util.Optional;

public interface ConsorcioService {

    Consorcio getConsorcioById(Long id);

    List<Consorcio> getConsorcioByInspectorId(Long idInspector);
}
