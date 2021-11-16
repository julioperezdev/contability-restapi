package dev.julioperez.contability.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImplementation implements WorkerService{

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImplementation(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.getAllWorkers();
    }

    @Override
    public Worker getWorkerById(Long id) {
        return workerRepository.getWorkerById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<Worker> getWorkerByIdConsorcio(Long idConsorcio) {
        return workerRepository.getWorkerByIdConsorcio(idConsorcio);
    }
}
