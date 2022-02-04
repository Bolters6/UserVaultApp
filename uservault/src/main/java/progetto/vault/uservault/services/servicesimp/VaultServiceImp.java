package progetto.vault.uservault.services.servicesimp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progetto.vault.uservault.models.Potion;
import progetto.vault.uservault.models.Sword;
import progetto.vault.uservault.models.Vault;
import progetto.vault.uservault.repository.PotionRepository;
import progetto.vault.uservault.repository.SwordRepository;
import progetto.vault.uservault.repository.VaultRepository;
import progetto.vault.uservault.services.servicesinterface.VaultService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VaultServiceImp implements VaultService {

	private final VaultRepository vaultRepo;
	private final SwordRepository swordRepository;
	private final PotionRepository potionRepository;

	@Override
	public Vault createVault(Vault vault) {
		log.info("Vault creato con esito");
		return vaultRepo.save(vault);
	}

	@Override
	public Vault updateVault(Vault vault){
		if(vaultRepo.existsById(vault.getIdv())){
			log.info("Vault updated con esito");
			return vaultRepo.save(vault);
		}
		throw new IllegalStateException("Vault non esistente");
	}

	@Override
	public Vault getVault(Long id) {
		if(vaultRepo.existsById(id)){
			return vaultRepo.findByIdv(id);
		}
		throw new IllegalStateException("VAULT NON ESISTENTE");
	}

	@Override
	public void addSwordVault(Long id, Sword sword) {
		if(vaultRepo.existsById(id) && swordRepository.existsById(sword.getIds())){
			vaultRepo.getById(id).getSwords().add(sword);
			log.info("Sword aggiunta con esito");
			return;
		}
		throw new IllegalStateException("SWORD O VAULT NON ESISTE");
	}

	@Override
	public void addPotionVault(Long id, Potion potion) {
		if(vaultRepo.existsById(id) && potionRepository.existsById(potion.getPotionid())){
			vaultRepo.findByIdv(id).getPotions().add(potion);
			log.info("Pozione aggiunta con esito");
			return;
		}
		throw new IllegalStateException("POTION O VAULT NON ESISTE");
	}

	@Override
	public void deleteSwordVault(Long id, Long ids) {
		if(vaultRepo.existsById(id) && swordRepository.existsById(ids)){
			vaultRepo.findByIdv(id).getSwords().remove(swordRepository.findByIds(ids));
			log.info("Sword rimossa con esito");
			return;
		}
		throw new IllegalStateException("VAULT O SWORD NON SI POSSONO RIMUOVERE");
	}

	@Override
	public void deletePotionVault(Long id, Long idp) {
		if (vaultRepo.existsById(id) && potionRepository.existsById(idp)) {
			vaultRepo.findByIdv(id).getPotions().remove(potionRepository.findByPotionid(idp));
			log.info("Pozione rimossa con esito");
			return;
		}
		throw new IllegalStateException("VAULT O POTION NON SI POSSONO RIMUOVERE");
	}
}
