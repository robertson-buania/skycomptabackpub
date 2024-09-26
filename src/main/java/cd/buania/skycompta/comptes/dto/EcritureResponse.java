package cd.buania.skycompta.comptes.dto;

import cd.buania.skycompta.shared.DEVISE;
import cd.buania.skycompta.shared.TYPECOMPTE;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record EcritureResponse(
        Long id,
        Long fk_ecriture,
        String journal_libelle,
        List<OperationResponse> operationResponses,
        Double montant_debit,
        Double montant_credit,
        String referencepiece,
        String libelle,
        Date dateecriture,
        Date datecreation,
        DEVISE devise
) {
}
