package progetto.vault.uservault.services.servicesimp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progetto.vault.uservault.models.Role;
import progetto.vault.uservault.repository.RoleRepository;
import progetto.vault.uservault.services.servicesinterface.RoleService;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Role createRole(Role role) {
        if(roleRepository.existsByRoleName(role.getRoleName())){
            throw new IllegalStateException("Role Esistente");
        }
        log.info("Role Creato");
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(String roleName) {
        if(roleRepository.existsByRoleName(roleName)){
            return roleRepository.getByRoleName(roleName);
        }
        throw new IllegalStateException("Role Non Esistente");
    }

    @Override
    public void deleteRole(Long id) {
        if(roleRepository.existsById(id)){
            log.info("Role Eliminato");
            roleRepository.deleteById(id);
        }
        throw new IllegalStateException("Role non si puo Rimuovere");
    }

    @Override
    public Role updateRole(Role role) {
       if(roleRepository.existsByRoleName(role.getRoleName())){
           log.info("Role Updated");
           return roleRepository.save(role);
       }
       throw new IllegalStateException("Role non Esiste");
    }
}
