package cd.buania.skycompta.comptes.service;


import cd.buania.skycompta.comptes.model.Compte;
import cd.buania.skycompta.comptes.model.Ohadacompte;
import cd.buania.skycompta.comptes.repo.CompteRepository;
import cd.buania.skycompta.comptes.repo.OhadacompteRepository;
import cd.buania.skycompta.shared.erreur.BuaniaErreur;
import cd.buania.skycompta.shared.erreur.BuaniaErreurService;
import cd.buania.skycompta.shared.erreur.SOURCE_ERROR;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    private final CompteRepository compteRepository;
    private  final OhadacompteRepository ohadacompteRepository;
    final
    BuaniaErreurService buaniaErreurService;

    public CompteService(CompteRepository compteRepository, OhadacompteRepository ohadacompteRepository, BuaniaErreurService buaniaErreurService) {
        this.compteRepository = compteRepository;
        this.ohadacompteRepository = ohadacompteRepository;
        this.buaniaErreurService = buaniaErreurService;
    }


    /**
     * METHODE DE CREATION D'UNN COMPTE COMPTABLE
     * @param compte
     * @return
     */
    public Compte createCompte(Compte compte) {

        try {
            Compte compte2=Compte.builder()

                    .code(compte.getCode())
                    .intitule(compte.getIntitule())
                    .typecompte(compte.getTypecompte())
                    //.ohadacompte(ohadacompteRepository.findById(compte.getOhadacompte().getId()).get())

                    .build();

            return compteRepository.save(compte2);

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
    public List<Compte> getAllComptes(){

        try {
            var ohadacomptes=new ArrayList<Compte>();
            var comptes=compteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            comptes.forEach(data->{
                ohadacomptes.add(Compte.builder()
                        .id(data.getId())
                        .code(data.getCode())
                        .intitule(data.getIntitule())
                        .ohadacompte(
                                        Ohadacompte.builder()
                                        .id(data.getId())
                                        
                                        .code(Long.valueOf(data.getCode()))
                                        .intitule(data.getIntitule())

                                        .build()
                                )
                                .typecompte(data.getTypecompte())

                        .build()
            );
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
    public Optional<Compte> getOneClasseByIf(Long id){
        return  compteRepository.findById(id);
    }


    public boolean updateCompte(Compte compte) {

        try {
             compteRepository.save(compte);

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
