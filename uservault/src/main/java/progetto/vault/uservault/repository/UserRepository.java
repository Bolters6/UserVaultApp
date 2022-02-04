package progetto.vault.uservault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progetto.vault.uservault.models.Utente;

@Repository
public interface UserRepository extends JpaRepository<Utente, Long>{

    boolean existsByUserName(String userName);

    Utente findByUserName(String username);

    boolean existsByEmail(String email);
}
