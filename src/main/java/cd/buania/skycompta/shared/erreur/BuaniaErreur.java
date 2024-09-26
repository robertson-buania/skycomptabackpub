package cd.buania.skycompta.shared.erreur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity(name = "BuaniaErreurShared")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuaniaErreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private SOURCE_ERROR source;
    //@DateTimeFormat
    private Date datecreate;


}
