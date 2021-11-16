package dev.julioperez.contability.inspector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Long> {

    @Query(value = "SELECT * FROM INSPECTOR", nativeQuery = true)
    Optional<List<Inspector>> getAllInspectors();

    @Query(value = "SELECT * FROM INSPECTOR WHERE id = :id ", nativeQuery = true)
    Optional<Inspector> getInspectorById(@Param("id") Long id);

}
