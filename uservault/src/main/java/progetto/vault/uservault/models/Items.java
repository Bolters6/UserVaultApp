package progetto.vault.uservault.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@JsonInclude(NON_NULL)
@AllArgsConstructor
@Data
public class Items {
    private Integer bless;
    private Integer souls;
    private Integer denaro;
    @JsonInclude(NON_EMPTY)
    private List<Potion> potions;
    @JsonInclude(NON_EMPTY)
    private List<Sword> swords;
    private String wings;
}
