package progetto.vault.uservault.services.servicesinterface;

import progetto.vault.uservault.models.Potion;

public interface PotionService {
	Potion createPotion(Potion potion);
    Potion getPotion(Long id);
    void deletePotion(Long id);
}
