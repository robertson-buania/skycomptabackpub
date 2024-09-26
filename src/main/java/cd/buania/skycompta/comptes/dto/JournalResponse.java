package cd.buania.skycompta.comptes.dto;

import lombok.Builder;

@Builder
public record JournalResponse(
        String code,
        Long id,
        String libelle

) {
}
