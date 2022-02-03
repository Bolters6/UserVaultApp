package progetto.vault.uservault.services.servicesimp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progetto.vault.uservault.models.Potion;
import progetto.vault.uservault.repository.PotionRepository;
import progetto.vault.uservault.services.servicesinterface.PotionService;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PotionServiceImp implements PotionService {

	private final PotionRepository potionRepo;

	@Override
	public Potion createPotion(Potion potion) {
		log.info("Pozione creata");
		return potionRepo.save(potion);
	}

	@Override
	public Potion getPotion(Long id) {
		if (!potionRepo.existsById(id)) {
			throw new IllegalStateException("POTION NON ESISTENTE");
		}
		return potionRepo.findByPotionid(id);
	}

	@Override
	public void deletePotion(Long id) {
		if (!potionRepo.existsById(id)) {
			throw new IllegalStateException("POTION NON SI PUO RIMUOVERE");
		}
		log.info("Pozione rimossa");
		potionRepo.delete(potionRepo.findByPotionid(id));
	}
}



