package cd.buania.skycompta.comptes.repo;

import cd.buania.skycompta.comptes.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}

