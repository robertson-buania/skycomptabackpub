package cd.buania.skycompta.comptes.repo;

import cd.buania.skycompta.comptes.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    public List<Operation> findOperationsByEcriture_Id(Long ecriture_id);
}

