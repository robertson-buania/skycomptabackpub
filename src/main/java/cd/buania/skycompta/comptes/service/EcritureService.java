package cd.buania.skycompta.comptes.service;

import cd.buania.skycompta.comptes.dto.EcritureRequest;
import cd.buania.skycompta.comptes.dto.EcritureResponse;
import cd.buania.skycompta.comptes.dto.OperationGrandLivreResponse;
import cd.buania.skycompta.comptes.dto.OperationResponse;
import cd.buania.skycompta.comptes.model.*;
import cd.buania.skycompta.comptes.repo.EcritureRepository;
import cd.buania.skycompta.comptes.repo.CompteRepository;
import cd.buania.skycompta.comptes.repo.JournalRepository;
import cd.buania.skycompta.shared.ResponseMessage;
import cd.buania.skycompta.shared.DEVISE;
import cd.buania.skycompta.shared.TYPECOMPTE;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class EcritureService {

    private final EcritureRepository ecritureRepository;
    private final CompteRepository compteRepository;
    private final JournalRepository journalRepository;

    public EcritureService(EcritureRepository ecritureRepository, CompteRepository compteRepository, JournalRepository journalRepository) {
        this.ecritureRepository = ecritureRepository;
        this.compteRepository = compteRepository;
        this.journalRepository = journalRepository;
    }

    @Transactional
    public ResponseMessage createEcriture(EcritureRequest request) {
        Ecriture ecriture = new Ecriture();
        ecriture.setJournal(journalRepository.findById(request.journal_id()).orElseThrow());
        ecriture.setMontantDebit(request.montant_debit());
        ecriture.setMontantCredit(request.montant_credit());
        ecriture.setLibelle(request.libelle());
        ecriture.setDateEcriture(request.dateecriture());
        ecriture.setDevise(request.devise());
        ecriture.setReferencePiece(request.referencepiece());
        ecriture.setOperations(new ArrayList<>());

        request.operationRequestList().forEach(opRequest -> { 
            Operation operation = new Operation();
            operation.setCompte(compteRepository.findById(opRequest.compte_id()).orElseThrow());
            operation.setMontant(opRequest.montant());
            operation.setTypecompte(opRequest.typecompte());
            operation.setEcriture(ecriture);
            operation.setDateCreation(LocalDateTime.now());
            ecriture.getOperations().add(operation);
        });

        ecritureRepository.save(ecriture);
        return new ResponseMessage(201, "Écriture créée avec succès", "true");
    }

    public List<EcritureResponse> getAllEcritures() {
        List<Ecriture> ecritures = ecritureRepository.findAll();
        return ecritures.stream().map(this::mapToEcritureResponse).collect(Collectors.toList());
    }

    public List<OperationGrandLivreResponse> getAllEcritureComptes() {
        List<Operation> operations = ecritureRepository.findAll().stream()
                .flatMap(e -> e.getOperations().stream())
                .collect(Collectors.toList());
        return operations.stream().map(this::mapToOperationGrandLivreResponse).collect(Collectors.toList());
    }

    public List<OperationGrandLivreResponse> getBalanceComptes() {
        List<Compte> comptes = compteRepository.findAll();
        return comptes.stream().map(this::calculateBalanceForCompte).collect(Collectors.toList());
    }

    @Transactional
    public boolean updateEcriture(EcritureRequest request) {
        Ecriture ecriture = ecritureRepository.findById(request.id()).orElseThrow();
        ecriture.setJournal(journalRepository.findById(request.journal_id()).orElseThrow());
        ecriture.setMontantDebit(request.montant_debit());
        ecriture.setMontantCredit(request.montant_credit());
        ecriture.setLibelle(request.libelle());
        ecriture.setDateEcriture(request.dateecriture());
        ecriture.setDevise(request.devise());
        ecriture.setReferencePiece(request.referencepiece());

        ecriture.getOperations().clear();
        request.operationRequestList().forEach(opRequest -> {
            Operation operation = new Operation();
            operation.setCompte(compteRepository.findById(opRequest.compte_id()).orElseThrow());
            operation.setMontant(opRequest.montant());
            operation.setTypecompte(opRequest.typecompte());
            operation.setEcriture(ecriture);
            operation.setDateCreation(LocalDateTime.now());
            ecriture.getOperations().add(operation);
        });

        ecritureRepository.save(ecriture);
        return true;
    }

    private EcritureResponse mapToEcritureResponse(Ecriture ecriture) {
        return EcritureResponse.builder()
            .id(ecriture.getId())
         //   .journal_id(ecriture.getJournal().getId())
            .journal_libelle(ecriture.getJournal().getLibelle())
            .montant_debit(ecriture.getMontantDebit())
            .montant_credit(ecriture.getMontantCredit())
            .libelle(ecriture.getLibelle())
            .dateecriture(ecriture.getDateEcriture())
            .devise(ecriture.getDevise())
            .referencepiece(ecriture.getReferencePiece())
            .operationResponses(ecriture.getOperations().stream().map(this::mapToOperationResponse).collect(Collectors.toList()))
            .build();
    }

    private OperationGrandLivreResponse mapToOperationGrandLivreResponse(Operation operation) {
        return OperationGrandLivreResponse.builder()
            .id(operation.getId())
            .compte_id(operation.getCompte().getId())
            .compte_code(operation.getCompte().getCode())
            .compte_intitule(operation.getCompte().getIntitule())
            .montant(operation.getMontant())
            .typecompte(operation.getTypecompte())
            .dateecriture(operation.getEcriture().getDateEcriture())
            .journal_intitule(operation.getEcriture().getJournal().getLibelle())
            .ecriture_intitule(operation.getEcriture().getLibelle())
            .build();
    }

    private OperationGrandLivreResponse calculateBalanceForCompte(Compte compte) {
        double soldeDebit = 0;
        double soldeCredit = 0;
        
        for (Operation operation : compte.getOperations()) {
            if (operation.getTypecompte() == TYPECOMPTE.DEBIT) {
                soldeDebit += operation.getMontant();
            } else {
                soldeCredit += operation.getMontant();
            }
        }
        
        double solde = soldeDebit - soldeCredit;
        TYPECOMPTE typeSolde = solde >= 0 ? TYPECOMPTE.DEBIT : TYPECOMPTE.CREDIT;
        
        return OperationGrandLivreResponse.builder()
            .id(null)
            .compte_id(compte.getId())
            .compte_code(compte.getCode())
            .compte_intitule(compte.getIntitule())
            .montant(Math.abs(solde))
            .typecompte(typeSolde)
            .dateecriture(null)
            .journal_intitule("Solde")
            .build();
    }

    private OperationResponse mapToOperationResponse(Operation operation) {
        return OperationResponse.builder()
            .id(operation.getId())
            .compte_id(operation.getCompte().getId())
            .montant(operation.getMontant())
            .typecompte(operation.getTypecompte())
            .build();
    }
}
