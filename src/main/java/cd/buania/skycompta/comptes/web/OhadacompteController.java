package cd.buania.skycompta.comptes.web;

import cd.buania.skycompta.comptes.model.Compte;
import cd.buania.skycompta.comptes.model.Ohadacompte;
import cd.buania.skycompta.comptes.service.CompteService;
import cd.buania.skycompta.comptes.service.OhadacompteService;
import cd.buania.skycompta.shared.info.INFO_SHARED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(INFO_SHARED.REQUEST_END+ "ohadacomptes")
@CrossOrigin(INFO_SHARED.AUTHORIZE_ORIGIN_FROM)
@RestController
public class OhadacompteController {
    @Autowired
    private OhadacompteService ohadacompteService;

    @PostMapping("new")
    public Ohadacompte createOhadaCompte(@RequestBody Ohadacompte compte) {

        return ohadacompteService.createOhadaCompte(compte);
    }


    @GetMapping("save")
    public boolean manuelSave() {

        return ohadacompteService.manuelSave();
    }
    @GetMapping("all")
    public List<Ohadacompte> getAllOhadaComptes() {
        return ohadacompteService.getAllOhadaComptes();
    }

    @PutMapping("edit")
    public boolean updateOhadaCompte(@RequestBody Ohadacompte compte){
        return  ohadacompteService.updateOhadacompte(compte);
    }
    // Other endpoints for update, delete, etc.
}