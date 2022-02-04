package progetto.vault.uservault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import progetto.vault.uservault.models.Vault;

@Repository
public interface VaultRepository extends JpaRepository<Vault, Long>{

    Vault findByIdv(Long id);

    boolean existsByIdv(Long id);
}
