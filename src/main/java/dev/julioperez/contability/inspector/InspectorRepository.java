package dev.julioperez.contability.inspector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Long> {

    @Query(value = "SELECT * FROM INSPECTOR", nativeQuery = true)
    Optional<List<Inspector>> getAllInspectors();

    //String query = "SELECT consorcio.email , inspector.phone FROM inspector JOIN consorcio = consorcio.idinspector = inspector.id";
    String query = "SELECT new dev.julioperez.contability.inspector.MegaSearchingDTO ( consorcio.email , inspector.phone ) FROM inspector JOIN consorcio = consorcio.idinspector = inspector.id";
    @Query(value = query, nativeQuery = true)
    List<MegaSearchingDTO> getDtoo();

    @Query(value = "SELECT TOP 1 cuil FROM WORKER", nativeQuery = true)
    String getString();

}
