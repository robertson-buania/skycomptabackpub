package cd.buania.skycompta.comptes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import cd.buania.skycompta.shared.DEVISE;
  

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Ecriture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;

    @OneToMany(mappedBy = "ecriture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations;

    private Double montantDebit;
    private Double montantCredit;
    private String libelle;
    private Date dateEcriture;
    private DEVISE devise;
    private String referencePiece;

    // Getters and setters
}