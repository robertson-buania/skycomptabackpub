package cd.buania.skycompta.comptes.service;


import cd.buania.skycompta.comptes.dto.JournalRequest;
import cd.buania.skycompta.comptes.dto.JournalResponse;
import cd.buania.skycompta.comptes.model.Compte;
import cd.buania.skycompta.comptes.model.Journal;
import cd.buania.skycompta.comptes.model.Ohadacompte;
import cd.buania.skycompta.comptes.repo.JournalRepository;
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
public class JournalService {

    private final JournalRepository journalRepository;
    final
    BuaniaErreurService buaniaErreurService;

    public JournalService(JournalRepository journalRepository, BuaniaErreurService buaniaErreurService) {
        this.journalRepository = journalRepository;

        this.buaniaErreurService = buaniaErreurService;
    }


    /**
     * METHODE DE CREATION D'UNN COMPTE COMPTABLE
     * @param journal
     * @return
     */
    public boolean createJournal(JournalRequest journal) {

        try {
            Journal journal1=Journal.builder()

                    .code(journal.code())
                    .libelle(journal.libelle())

                    .build();

             journalRepository.save(journal1);
             return  true;

        }catch (Exception e){

            buaniaErreurService.newErreur(BuaniaErreur.builder()
                    .source(SOURCE_ERROR.JOURNAL_CREATE)
                    .message(e.getMessage())
                    .datecreate(new Date())
                    .build());
            return  false;
        }

    }


    /**
     * METHODE DE RECUPERATION DES COMPTES
     * @return
     */
    public List<JournalResponse> getAllJournaux(){

        try {
            var journaux=new ArrayList<JournalResponse>();
            journalRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
            .forEach(data->{
                journaux.add(JournalResponse.builder()
                        .id(data.getId())
                        .code(data.getCode())
                        .libelle(data.getLibelle())
                        .build()
            );
            });
            return  journaux;

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
    public JournalResponse getOneJournalByIf(Long id){

           Journal journal=journalRepository.findById(id).get();
        return  JournalResponse.builder()
                        .code(journal.getCode())
                                .libelle(journal.getLibelle())
                .id(journal.getId())
                      .build();
    }


    public boolean updateJournal(JournalRequest journal) {

        try {
            var currentJournal= journalRepository.findById(journal.id()).get();

            currentJournal.setCode(journal.code());
            currentJournal.setLibelle(journal.libelle());

             journalRepository.save(currentJournal);

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
