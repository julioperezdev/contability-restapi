package dev.julioperez.contability.consorcio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsorcioServiceImplementation implements ConsorcioService{

    private final ConsorcioRepository consorcioRepository;

    @Autowired
    public ConsorcioServiceImplementation(ConsorcioRepository consorcioRepository) {
        this.consorcioRepository = consorcioRepository;
    }

    @Override
    public Consorcio getConsorcioById(Long id) {
        Consorcio consorcio = consorcioRepository.getConsorcioById(id).orElseThrow(IllegalStateException::new);
        return consorcio;
    }

    @Override
    public List<Consorcio> getConsorcioByInspectorId(Long idInspector) {
        return consorcioRepository.getConsorcioByInspectorId(idInspector);
    }
}
