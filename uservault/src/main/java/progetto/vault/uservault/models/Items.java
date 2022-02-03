package progetto.vault.uservault.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class Items {
    private Integer bless;
    private Integer souls;
    private Integer denaro;
    private List<Potion> potions;
    private List<Sword> swords;
    private String wings;
}
