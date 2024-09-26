package cd.buania.skycompta.comptes.service;

import cd.buania.skycompta.comptes.model.Classe;
import cd.buania.skycompta.comptes.repo.ClasseRepository;
import cd.buania.skycompta.shared.erreur.BuaniaErreur;
import cd.buania.skycompta.shared.erreur.BuaniaErreurService;
import cd.buania.skycompta.shared.erreur.SOURCE_ERROR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private BuaniaErreurService buaniaErreurService;

    /**
     * METHODE DE CREATION D'UNE CLASSE COMPTABLE

     * @return
     */

    public boolean  manuelSaveClasse(){

        try {
            classeRepository.save(new Classe(null,"1", "Capitaux propres",null));
            classeRepository.save (new Classe(null,"2", "Actif circulant",null));
            classeRepository.save  (new Classe(null,"3", "Passif circulant",null));
            classeRepository.save(new Classe(null,"4", "Trésorerie",null));
            classeRepository.save(    new Classe(null,"5", "Produits d'exploitation",null));
            classeRepository.save    (new Classe(null,"6", "Charges d'exploitation",null));
            classeRepository.save( new Classe(null,"7", "Produits financiers",null));
            classeRepository.save    ( new Classe(null,"8", "Charges financières",null));
            classeRepository.save      (new Classe(null,"9", "Résultat",null));
            return  true;
        }catch (Exception e){
            return  false;
        }


    }
    public Classe createClasse(Classe classe) {
        try {
            return classeRepository.save(classe);

        }catch (Exception e){

            System.out.println(e.getMessage());

         buaniaErreurService.newErreur(  BuaniaErreur.builder()
                 .source(SOURCE_ERROR.CLASSE_CREATE)
                 .message(e.getMessage())
                 .datecreate(new Date())
                 .build()) ;
            return  null;
        }

    }
    /**
     * METHODE DE RECUPERATION DES CLASSES
     * @return
     */
   public List<Classe> getAllClasses(){
       try {
           var classes=new ArrayList<Classe>();
           classeRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).forEach(data->{

               classes.add(Classe.builder().
                       code(data.getCode())
                               .id(data.getId())
                               .intitule(data.getIntitule())

                       .build());
           });


           return  classes;

       }catch (Exception e){

           buaniaErreurService.newErreur(  BuaniaErreur.builder()
                   .source(SOURCE_ERROR.CLASSE_LIST)
                   .message(e.getMessage())
                   .datecreate(new Date())
                   .build()) ;
           return  null;
       }

    }

    /**
     * RECUPERATION D'UNE SEULLE CLASSE
     * @param id
     * @return
     */
   public Optional<Classe> getOneClasseByIf(Long id){

       try {
           return  classeRepository.findById(id);

       }catch (Exception e){

           System.out.println(e.getMessage());

           buaniaErreurService.newErreur(  BuaniaErreur.builder()
                   .source(SOURCE_ERROR.CLASSE_ONE)
                   .message(e.getMessage())
                   .datecreate(new Date())
                   .build()) ;
           return  null;
       }

    }


}
