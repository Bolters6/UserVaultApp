package progetto.vault.uservault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import progetto.vault.uservault.models.Sword;

@Repository
public interface SwordRepository extends JpaRepository<Sword, Long>{
    Sword findByIds(Long id);
}
