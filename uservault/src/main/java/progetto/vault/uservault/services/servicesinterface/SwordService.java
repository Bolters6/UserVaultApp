package progetto.vault.uservault.services.servicesinterface;

import progetto.vault.uservault.models.Sword;

public interface SwordService {
    Sword createSword(Sword sword);
    Sword getSword(Long id);
    void deleteSword(Long id);
}
