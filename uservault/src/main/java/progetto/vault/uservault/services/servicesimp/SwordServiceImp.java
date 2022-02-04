package progetto.vault.uservault.services.servicesimp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progetto.vault.uservault.models.Sword;
import progetto.vault.uservault.repository.SwordRepository;
import progetto.vault.uservault.services.servicesinterface.SwordService;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SwordServiceImp implements SwordService {

	private final SwordRepository swordRepo;

	@Override
	public Sword createSword(Sword sword) {
		log.info("Sword creata");
		return swordRepo.save(sword);
	}

	@Override
	public Sword getSword(Long id) {
		if(swordRepo.existsById(id)){
			return swordRepo.findByIds(id);
		}
		throw new IllegalStateException("SWORD NON ESISTENTE");
	}

	@Override
	public void deleteSword(Long id) {
		if(swordRepo.existsById(id)){
			 swordRepo.deleteById(id);
			 log.info("Sword rimossa");
			 return;
		}
		throw new IllegalStateException("SWORD NON SI PUO RIMUOVERE");
	}

}
