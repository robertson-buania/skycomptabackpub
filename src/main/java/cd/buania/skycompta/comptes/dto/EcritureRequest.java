package cd.buania.skycompta.comptes.dto;

import cd.buania.skycompta.shared.DEVISE;
import lombok.Builder;

import java.util.Date;
import java.util.List;
@Builder
public record EcritureRequest(
        Long id,
        Long journal_id,
        List<OperationRequest> operationRequestList,
        Double montant_debit,
        Double montant_credit,
        String libelle,
        Date dateecriture,
        String referencepiece,
        DEVISE devise
) {
}


