package progetto.vault.uservault.services.servicesinterface;

import progetto.vault.uservault.models.Utente;

public interface UserService {
    Utente registerUser(Utente user);
    Utente updateUser(Utente user);
    Utente getUser(String username);
    void addVaultUser(String username, Long id);
}
