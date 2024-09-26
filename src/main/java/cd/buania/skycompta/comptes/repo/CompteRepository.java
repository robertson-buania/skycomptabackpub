package cd.buania.skycompta.comptes.repo;

import cd.buania.skycompta.comptes.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}


