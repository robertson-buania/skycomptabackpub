package cd.buania.skycompta.comptes.web;

import cd.buania.skycompta.comptes.model.Classe;
import cd.buania.skycompta.comptes.service.ClasseService;
import cd.buania.skycompta.shared.info.INFO_SHARED;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(INFO_SHARED.REQUEST_END+ "classes/")
@CrossOrigin(INFO_SHARED.AUTHORIZE_ORIGIN_FROM)
public class ClasseController {
    @Autowired
    private ClasseService classeService;

    @PostMapping("new")
    public Classe createClasse(@RequestBody Classe classe) {

        //Classe classe1=new Classe(null,"1","",null);
        classeService.createClasse(new Classe(null,"1", "Capitaux propres",null));
        classeService.createClasse (new Classe(null,"2", "Actif circulant",null));
        classeService.createClasse  (new Classe(null,"3", "Passif circulant",null));
        classeService.createClasse(new Classe(null,"4", "Trésorerie",null));
        classeService.createClasse(    new Classe(null,"5", "Produits d'exploitation",null));
        classeService.createClasse    (new Classe(null,"6", "Charges d'exploitation",null));
        classeService.createClasse( new Classe(null,"7", "Produits financiers",null));
        classeService.createClasse    ( new Classe(null,"8", "Charges financières",null));
        classeService.createClasse      (new Classe(null,"9", "Résultat",null));

        return classeService.createClasse(classe);
    }
    @GetMapping("save")
    public boolean manuelSaveClasse() {

        //Classe classe1=new Classe(null,"1","",null);

        return classeService.manuelSaveClasse();
    }

    @GetMapping("all")
    public List<Classe> getAllClasses() {
        return classeService.getAllClasses();
    }
    // Other endpoints for update, delete, etc.
}