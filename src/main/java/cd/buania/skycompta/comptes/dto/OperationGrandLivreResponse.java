package cd.buania.skycompta.comptes.dto;


import cd.buania.skycompta.shared.DEVISE;
import cd.buania.skycompta.shared.TYPECOMPTE;
import lombok.Builder;

import java.util.Date;

@Builder
public record OperationGrandLivreResponse(
        Long id,
        String compte_intitule,
        TYPECOMPTE typecompte,
        Long compte_id,
        Double montant,
        String compte_code,
        String journal_intitule,
        String ecriture_intitule,
        DEVISE devise,
        Date dateecriture
) {
}

