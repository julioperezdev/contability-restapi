package dev.julioperez.contability.inspector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InspectorServiceImplementation implements InspectorService{

    private final InspectorUtils inspectorUtils;
    private final InspectorRepository inspectorRepository;

    @Autowired
    public InspectorServiceImplementation(InspectorUtils inspectorUtils, InspectorRepository inspectorRepository) {
        this.inspectorUtils = inspectorUtils;
        this.inspectorRepository = inspectorRepository;
    }


    @Override
    public Optional<List<Inspector>> getAllInspectors() {
        Optional<List<Inspector>> allInspectors = inspectorRepository.getAllInspectors();
        if(inspectorUtils.checkIfListIsPresentOrEmpty(allInspectors)){
            throw new IllegalStateException("The list is empty");
        }
        return allInspectors;
    }

    @Override
    public Inspector getInspectorById(Long id) {
        return inspectorRepository.getInspectorById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Optional<Inspector> saveInspector(Inspector inspector) {
        return Optional.empty();
    }

    @Override
    public Optional<Inspector> updateInspectorById(Inspector inspector) {
        return Optional.empty();
    }

    @Override
    public void deleteInspectorById(Long inspector) {

    }

    @Override
    public Optional<List<MegaSearchingDTO>> getMegaSearchingByInspectorId(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<SuperSearchingDTO>> getSuperSearchingByInspectorId(int id) {
        return Optional.empty();
    }
}
