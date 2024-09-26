package cd.buania.skycompta.comptes.dto;

import cd.buania.skycompta.shared.TYPECOMPTE;
import lombok.Builder;

@Builder
public record OperationResponse(
        Long id,
        String compte_intitule,
        TYPECOMPTE typecompte,
        Long compte_id,
        Double montant,
        Long compte_code
) {
}
