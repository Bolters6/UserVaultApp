package progetto.vault.uservault.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progetto.vault.uservault.models.Items;
import progetto.vault.uservault.models.Sword;
import progetto.vault.uservault.models.Vault;
import progetto.vault.uservault.services.servicesinterface.PotionService;
import progetto.vault.uservault.services.servicesinterface.SwordService;
import progetto.vault.uservault.services.servicesinterface.VaultService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "app/vault")
public class VaultController {

    private final VaultService vaultService;
    private final SwordService swordService;
    private final PotionService potionService;

    @GetMapping(path = "/{id}/getvault")
    public ResponseEntity<Vault> getVault(@PathVariable Long id){
        return ResponseEntity.ok().body(vaultService.getVault(id));
    }

    @GetMapping(path="/{id}/getitemsvault")
    public ResponseEntity<Items> getItemsVault(@PathVariable Long id){
        Vault vault = vaultService.getVault(id);
        return ResponseEntity.ok().body(new Items(vault.getBless(),vault.getSouls(), vault.getDenaro(), vault.getPotions()
                                        ,vault.getSwords(), vault.getWings()));
    }

    @PostMapping(path = "/{id}/addsword")
    public ResponseEntity<?> addSword(@PathVariable Long id, @RequestBody @Valid Sword sword){
        swordService.createSword(sword);
        vaultService.addSwordVault(id,sword);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{id}/addpotion/{idpotion}")
    public ResponseEntity<?> addPotion(@PathVariable Long id, @PathVariable Long idpotion){
        vaultService.addPotionVault(id, potionService.getPotion(idpotion));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}/deletesword/{idsword}")
    public ResponseEntity<?> deleteSword(@PathVariable Long id, @PathVariable Long idsword){
        vaultService.deleteSwordVault(id,idsword);
        swordService.deleteSword(idsword);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}/deletepotion/{idpotion}")
    public ResponseEntity<?> deletePotion(@PathVariable Long id, @PathVariable Long idpotion){
        vaultService.deletePotionVault(id,idpotion);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{id}/addbless")
    public ResponseEntity<Vault> addBless(@PathVariable Long id, @RequestParam Integer blessQuantity){
        Integer bq = vaultService.getVault(id).getBless();
        vaultService.getVault(id).setBless(bq + blessQuantity);
        return ResponseEntity.ok().body(vaultService.updateVault(vaultService.getVault(id)));
    }

    @PostMapping(path = "/{id}/addsoul")
    public ResponseEntity<Vault> addSoul(@PathVariable Long id, @RequestParam Integer soulQuantity){
        Integer sq = vaultService.getVault(id).getSouls();
        vaultService.getVault(id).setSouls(sq + soulQuantity);
        return ResponseEntity.ok().body(vaultService.updateVault(vaultService.getVault(id)));
    }

    @PostMapping(path = "/{id}/adddenaro")
    public ResponseEntity<Vault> addDenaro(@PathVariable Long id, @RequestParam Integer denaroQuantity){
        Integer dq = vaultService.getVault(id).getDenaro();
        vaultService.getVault(id).setDenaro(dq + denaroQuantity);
        return ResponseEntity.ok().body(vaultService.updateVault(vaultService.getVault(id)));
    }

    @DeleteMapping(path = "/{id}/deletebless")
    public ResponseEntity<Vault> deleteBless(@PathVariable Long id, @RequestParam Integer blessQuantity){
        Integer bq = vaultService.getVault(id).getBless();
        if(blessQuantity > bq){
            throw new IllegalStateException("Stai Cercando di rimuovere piu da quello che hai");
        }
        vaultService.getVault(id).setBless(bq - blessQuantity);
        return ResponseEntity.ok().body(vaultService.updateVault(vaultService.getVault(id)));
    }

    @DeleteMapping(path = "/{id}/deletesoul")
    public ResponseEntity<Vault> deleteSoul(@PathVariable Long id, @RequestParam Integer soulQuantity){
        Integer sq = vaultService.getVault(id).getSouls();
        if(soulQuantity > sq){
            throw new IllegalStateException("Stai Cercando di rimuovere piu da quello che hai");
        }
        vaultService.getVault(id).setSouls(sq - soulQuantity);
        return ResponseEntity.ok().body(vaultService.updateVault(vaultService.getVault(id)));
    }

    @DeleteMapping(path = "/{id}/deletedenaro")
    public ResponseEntity<Vault> deleteDenaro(@PathVariable Long id, @RequestParam Integer denaroQuantity){
        Integer dq = vaultService.getVault(id).getDenaro();
        if(denaroQuantity > dq){
            throw new IllegalStateException("Stai Cercando di rimuovere piu da quello che hai");
        }
        vaultService.getVault(id).setDenaro(dq - denaroQuantity);
        return ResponseEntity.ok().body(vaultService.updateVault(vaultService.getVault(id)));
    }

    @PostMapping(path = "/{id}/addwings")
    public ResponseEntity<Vault> addWings(@PathVariable Long id, @RequestParam Integer typeWings){
        Vault vault = vaultService.getVault(id);
        switch(typeWings){
            case 1:
                vault.setWings("Knight");
                break;
            case 2:
                vault.setWings("Wizard");
                break;
            case 3:
                vault.setWings("Elf");
                break;
            case 4:
                vault.setWings("Sumonner");
                break;
            default:
                vault.setWings("Baby Wings");
        }
        return ResponseEntity.ok().body(vaultService.updateVault(vault));
    }
    @DeleteMapping(path = "/{id}/deletewings")
    public ResponseEntity<Vault> deleteWings(@PathVariable Long id){
        vaultService.getVault(id).setWings(null);
        return ResponseEntity.ok().body(vaultService.updateVault(vaultService.getVault(id)));
    }

}
