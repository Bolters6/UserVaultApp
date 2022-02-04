package progetto.vault.uservault.services.servicesinterface;

import progetto.vault.uservault.models.Role;

public interface RoleService {
    Role createRole(Role role);
    Role getRole(String roleName);
    void deleteRole(Long id);
    Role updateRole(Role role);
}
