package cd.buania.skycompta.comptes.dto;

import lombok.Builder;

@Builder
public record JournalRequest(
        Long id,
        String code,

        String libelle
) {
}
