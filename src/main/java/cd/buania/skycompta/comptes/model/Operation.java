package cd.buania.skycompta.comptes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import cd.buania.skycompta.shared.TYPECOMPTE;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "ecriture_id")
    private Ecriture ecriture;

    private Double montant;

    
    private TYPECOMPTE typecompte;

    @Column(name = "datecreation")
    private LocalDateTime dateCreation;

    // Getters and setters
}

 