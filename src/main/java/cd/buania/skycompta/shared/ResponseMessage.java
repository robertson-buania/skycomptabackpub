package cd.buania.skycompta.shared;

import lombok.Builder;

@Builder
public record ResponseMessage(
        int code,
        String message,
        String source
) {
}
