package cd.buania.skycompta.comptes.dto;

import cd.buania.skycompta.shared.TYPECOMPTE;
import lombok.Builder;

@Builder
public record OperationRequest(
        TYPECOMPTE typecompte,
        Long compte_id,
        Double montant
) {

}

