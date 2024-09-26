package cd.buania.skycompta.comptes.web;

import cd.buania.skycompta.comptes.dto.EcritureRequest;
import cd.buania.skycompta.comptes.dto.EcritureResponse;
import cd.buania.skycompta.comptes.dto.OperationGrandLivreResponse; 
import cd.buania.skycompta.comptes.service.EcritureService;
import cd.buania.skycompta.shared.ResponseMessage;
import cd.buania.skycompta.shared.info.INFO_SHARED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(INFO_SHARED.AUTHORIZE_ORIGIN_FROM)
//@CrossOrigin(origins = "http://localhost:7777", allowedHeaders = "*")
//@RequestMapping("/api/v1/ecritures")
@RequestMapping(INFO_SHARED.REQUEST_END+ "ecritures")
public class EcritureController {

    private final EcritureService ecritureService;

    @Autowired
    public EcritureController(EcritureService ecritureService) {
        this.ecritureService = ecritureService;
    }

    @PostMapping("new")
    public ResponseMessage createEcriture(@RequestBody EcritureRequest ecritureRequest) {

        System.out.println("Ecriture");
        System.out.println(ecritureRequest.libelle());

        return ecritureService.createEcriture(ecritureRequest);
    }
    @GetMapping("all")
    public List<EcritureResponse> getAllEcritures() {
        return ecritureService.getAllEcritures();
    }

    @GetMapping("grandlivre")
    public List<OperationGrandLivreResponse> getAllEcritureComptes() {
        return ecritureService.getAllEcritureComptes();
    }

    @GetMapping("balance")
    public List<OperationGrandLivreResponse> getBalanceComptes() {
        return ecritureService.getBalanceComptes();
    }

    @PutMapping("edit")
    public boolean updateEcriture(@RequestBody EcritureRequest ecritureRequest){
        return  ecritureService.updateEcriture(ecritureRequest);
    }

    // @GetMapping("/comptes-resultat")
    // public ResponseEntity<List<OperationGrandLivreResponse>> getComptesResultat() {
    //     List<OperationGrandLivreResponse> comptesResultat = ecritureService.getComptesResultat();
    //     return ResponseEntity.ok(comptesResultat);
    // }
    // Other endpoints for update, delete, etc.
}