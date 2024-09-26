package cd.buania.skycompta.comptes.repo;

import cd.buania.skycompta.comptes.model.Ecriture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcritureRepository extends JpaRepository<Ecriture, Long> {
}

