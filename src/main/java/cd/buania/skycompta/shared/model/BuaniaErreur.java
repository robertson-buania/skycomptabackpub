package cd.buania.skycompta.shared.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "BuaniaErreurModel")
@Table(name = "buania_erreur")
public class BuaniaErreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datecreate")
    private LocalDateTime dateCreate;
 
    private String message;
 
    private String source;

    // Getters and setters
}