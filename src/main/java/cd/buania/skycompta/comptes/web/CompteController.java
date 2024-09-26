package cd.buania.skycompta.comptes.web;

import cd.buania.skycompta.comptes.model.Compte;
import cd.buania.skycompta.comptes.service.CompteService;
import cd.buania.skycompta.shared.info.INFO_SHARED;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(INFO_SHARED.REQUEST_END+ "comptes")
@CrossOrigin(INFO_SHARED.AUTHORIZE_ORIGIN_FROM)
@RestController
public class CompteController {
    @Autowired
    private CompteService compteService;
    @PostMapping("new")
    public Compte createCompte(@RequestBody Compte compte) {
        System.out.println(compte.toString());
        return compteService.createCompte(compte);
    }
    @GetMapping("all")
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    @PutMapping("edit")
    public boolean updateCompte(@RequestBody Compte compte){
        return  compteService.updateCompte(compte);
    }
    // Other endpoints for update, delete, etc.
}