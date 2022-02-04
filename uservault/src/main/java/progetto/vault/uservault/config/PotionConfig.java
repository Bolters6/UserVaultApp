package progetto.vault.uservault.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import progetto.vault.uservault.models.Potion;
import progetto.vault.uservault.repository.PotionRepository;
import java.util.List;

@Configuration
public class PotionConfig{

        @Bean(name = "Potion")
        CommandLineRunner commandLineRunner(PotionRepository potionRepository){
            return args -> {
                if(potionRepository.findAll().isEmpty()) {
                    Potion p1 = new Potion(null, "Soul", "+Damage", "Large");
                    Potion p2 = new Potion(null, "Soul", "+Damage", "Small");
                    Potion p3 = new Potion(null, "Bless", "+Speed", "Large");
                    Potion p4 = new Potion(null, "Bless", "+Speed", "Small");
                    Potion p5 = new Potion(null, "Healing", "Heal", "Large");
                    Potion p6 = new Potion(null, "Healing", "Heal", "Small");
                    Potion p7 = new Potion(null, "Mana", "+Mana", "Large");
                    Potion p8 = new Potion(null, "Mana", "+Mana", "Small");
                    Potion p9 = new Potion(null, "Antidota", "Reduce Venom", "Medium");
                    Potion p10 = new Potion(null, "Ale", "Drunk", "Medium");
                    potionRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
                }
            };
        }
}
