package progetto.vault.uservault.services.servicesinterface;

import progetto.vault.uservault.models.Potion;
import progetto.vault.uservault.models.Sword;
import progetto.vault.uservault.models.Vault;

public interface VaultService {
    Vault createVault(Vault vault);
    Vault updateVault(Vault vault);
    Vault getVault(Long id);
    void addSwordVault(Long id, Sword sword);
    void addPotionVault(Long id, Potion potion);
    void deleteSwordVault(Long id, Long ids);
    void deletePotionVault(Long id, Long idp);
}
