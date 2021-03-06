package dev.julioperez.contability.inspector;

import dev.julioperez.contability.shared.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inspector")
public class InspectorController {

    private final String DONT_HAVE_INSPECTORS = "Actually dont have inspectors";
    private final String INSPECTORS_FOUNDED = "Inspectors was founded";

    private final InspectorService inspectorService;
    private final InspectorUtils inspectorUtils;

    @Autowired
    public InspectorController(InspectorService inspectorService, InspectorUtils inspectorUtils) {
        this.inspectorService = inspectorService;
        this.inspectorUtils = inspectorUtils;
    }

    @GetMapping("/getall")
    public RestResponse<Optional<List<Inspector>>> getAllInspectors(){
        String message = DONT_HAVE_INSPECTORS;
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
        Optional<List<Inspector>> allInspectors = inspectorService.getAllInspectors();
        if( !inspectorUtils.checkIfListIsPresentOrEmpty(allInspectors)){
            message = INSPECTORS_FOUNDED;
            httpStatus = HttpStatus.FOUND;
        }
        return new RestResponse<>(httpStatus, message, allInspectors);
    }

}
