package cd.buania.skycompta.comptes.service;


import cd.buania.skycompta.comptes.model.Ohadacompte;
import cd.buania.skycompta.comptes.repo.ClasseRepository;
import cd.buania.skycompta.comptes.repo.OhadacompteRepository;
import cd.buania.skycompta.shared.erreur.BuaniaErreur;
import cd.buania.skycompta.shared.erreur.BuaniaErreurService;
import cd.buania.skycompta.shared.erreur.SOURCE_ERROR;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OhadacompteService {

    private final OhadacompteRepository ohadacompteRepository;
    private final ClasseRepository classeRepository;
    final
    BuaniaErreurService buaniaErreurService;

    public OhadacompteService(OhadacompteRepository ohadacompteRepository, ClasseRepository classeRepository, BuaniaErreurService buaniaErreurService) {
        this.ohadacompteRepository = ohadacompteRepository;
        this.classeRepository = classeRepository;
        this.buaniaErreurService = buaniaErreurService;
    }


    public boolean manuelSave(){
        try {
            Ohadacompte[] comptes = {
                    Ohadacompte.builder()
                            .code(101L)
                            .intitule("Capital social")
                            .classe(classeRepository.findById(1L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(102L)
                            .intitule("Primes d'émission")
                            .classe(classeRepository.findById(1L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(201L)
                            .intitule("Clients")
                            .classe(classeRepository.findById(2L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(301L)
                            .intitule("Fournisseurs")
                            .classe(classeRepository.findById(3L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(401L)
                            .intitule("Banque")
                            .classe(classeRepository.findById(4L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(501L)
                            .intitule("Ventes")
                            .classe(classeRepository.findById(5L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(601L)
                            .intitule("Achats")
                            .classe(classeRepository.findById(6L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(701L)
                            .intitule("Produits d'exploitation")
                            .classe(classeRepository.findById(7L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(801L)
                            .intitule("Impôts et taxes")
                            .classe(classeRepository.findById(8L).get())
                            .build(),

                    Ohadacompte.builder()
                            .code(901L)
                            .intitule("Résultat de l'exercice")
                            .classe(classeRepository.findById(9L).get())
                            .build()
            };
            ohadacompteRepository.saveAll(Arrays.stream(comptes).toList());
            return true;

        }catch (Exception e){

            buaniaErreurService.newErreur(BuaniaErreur.builder()
                    .source(SOURCE_ERROR.COMPTE_CREATE)
                    .message(e.getMessage())
                    .datecreate(new Date())
                    .build());
            return  false;
        }

    }
    /**
     * METHODE DE CREATION D'UNN COMPTE COMPTABLE
     * @param compte
     * @return
     */


    public Ohadacompte createOhadaCompte(Ohadacompte compte) {

        try {


            return ohadacompteRepository.save(compte);

        }catch (Exception e){

            buaniaErreurService.newErreur(BuaniaErreur.builder()
                    .source(SOURCE_ERROR.COMPTE_CREATE)
                    .message(e.getMessage())
                    .datecreate(new Date())
                    .build());
            return  null;
        }

    }

    /**
     * METHODE DE RECUPERATION DES COMPTES
     * @return
     */
    public List<Ohadacompte> getAllOhadaComptes(){

        try {
            var ohadacomptes=new ArrayList<Ohadacompte>();
            var comptes=ohadacompteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            comptes.forEach(data->{
                ohadacomptes.add(Ohadacompte.builder()
                                .id(data.getId())
                                .code(data.getCode())
                                .intitule(data.getIntitule())

                        .build());
            });
            return  ohadacomptes;

        }catch (Exception e){

            System.out.println(e.getMessage());

            buaniaErreurService.newErreur(BuaniaErreur.builder()
                    .source(SOURCE_ERROR.COMPTE_LIST)
                    .message(e.getMessage())
                    .datecreate(new Date())
                    .build());
            return  null;
        }

    }

    /**
     * RECUPERATION D'UN SEUL COMPTE
     * @param id
     * @return
     */
    public Optional<Ohadacompte> getOneClasseByIf(Long id){
        return  ohadacompteRepository.findById(id);
    }


    public boolean updateOhadacompte(Ohadacompte compte) {

        try {
            ohadacompteRepository.save(compte);

            return  true;
        }catch (Exception e){
            buaniaErreurService.newErreur(
                    BuaniaErreur.builder()
                            .source(SOURCE_ERROR.COMPTE_UPDATE)
                            .message(e.getMessage())
                            .datecreate(new Date())
                            .build()
            );
            return  false;

        }


    }
}
