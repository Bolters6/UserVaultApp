package progetto.vault.uservault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import progetto.vault.uservault.models.Potion;

@Repository
public interface PotionRepository extends JpaRepository<Potion, Long> {

    boolean existsByNomePozione(String potionName);

    Potion findByNomePozione(String potionName);

    Potion findByPotionid(Long id);
}
