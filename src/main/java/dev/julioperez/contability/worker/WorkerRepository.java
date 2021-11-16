package dev.julioperez.contability.worker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value = "SELECT * FROM WORKER ", nativeQuery = true)
    List<Worker> getAllWorkers();

    @Query(value = "SELECT * FROM WORKER WHERE id = :id ", nativeQuery = true)
    Optional<Worker> getWorkerById(@Param("id") Long id);

    @Query(value = "SELECT * FROM WORKER WHERE idConsorcio = :idConsorcio ", nativeQuery = true)
    List<Worker> getWorkerByIdConsorcio(@Param("idConsorcio") Long idConsorcio);
}
