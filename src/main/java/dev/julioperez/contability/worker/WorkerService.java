package dev.julioperez.contability.worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers();

    Worker getWorkerById(Long id);

    List<Worker> getWorkerByIdConsorcio(Long idConsorcio);
}
