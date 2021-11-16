package dev.julioperez.contability.consorcio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsorcioRepository extends JpaRepository<Consorcio, Long> {

    @Query(value = "SELECT * FROM Consorcio WHERE id = :id ", nativeQuery = true)
    Optional<Consorcio> getConsorcioById(@Param("id") Long id);

    @Query(value = "SELECT * FROM Consorcio WHERE idInspector = :idInspector ", nativeQuery = true)
    List<Consorcio> getConsorcioByInspectorId(@Param("idInspector") Long idInspector);

}
