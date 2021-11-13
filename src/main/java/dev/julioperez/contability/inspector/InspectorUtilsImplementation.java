package dev.julioperez.contability.inspector;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InspectorUtilsImplementation implements InspectorUtils{

    /**
     * @param list
     * @return return booleans in true when check that the value is wrong
     */
    @Override
    public boolean checkIfListIsPresentOrEmpty(Optional<List<Inspector>> list) {
            return list.orElseThrow(IllegalArgumentException::new).isEmpty();
    }
}
