package dev.julioperez.contability.inspector;

import java.util.List;
import java.util.Optional;

public interface InspectorUtils {

    //boolean checkIfListIsPresent(Optional<List<Inspector>> list);

    //boolean checkIfListIsEmpty(List<Inspector> list);

    boolean checkIfListIsPresentOrEmpty(Optional<List<Inspector>> list);
}
