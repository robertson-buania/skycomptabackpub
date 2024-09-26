package cd.buania.skycompta.comptes.model;

import cd.buania.skycompta.shared.TYPECOMPTE;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;
    @Column(length = 15)
    private String code;
    private TYPECOMPTE typecompte;

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Compte parent;

    @OneToOne()
    private Ohadacompte ohadacompte;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compte> children = new ArrayList<>();

}
