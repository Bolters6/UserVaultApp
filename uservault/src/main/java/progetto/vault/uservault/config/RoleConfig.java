package progetto.vault.uservault.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import progetto.vault.uservault.models.Role;
import progetto.vault.uservault.repository.RoleRepository;

import java.util.List;

@Configuration
public class RoleConfig {

    @Bean(name="Role")
    CommandLineRunner commandLineRunner(RoleRepository roleRepository){
        return args -> {
            if(roleRepository.findAll().isEmpty()) {
                Role r1 = new Role(null, "Admin");
                Role r2 = new Role(null, "User");
                Role r3 = new Role(null, "Super Admin");
                roleRepository.saveAll(List.of(r1, r2, r3));
            }
        };
    }
}
