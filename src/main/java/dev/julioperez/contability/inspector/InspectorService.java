package dev.julioperez.contability.inspector;

import java.util.List;
import java.util.Optional;

public interface InspectorService {

    Optional<List<Inspector>> getAllInspectors();

    Inspector getInspectorById(Long id);

    Optional<Inspector> saveInspector(Inspector inspector);

    Optional<Inspector> updateInspectorById(Inspector inspector);

    void deleteInspectorById(Long inspector);

    Optional<List<MegaSearchingDTO>> getMegaSearchingByInspectorId(int id);

    Optional<List<SuperSearchingDTO>> getSuperSearchingByInspectorId(int id);
}
