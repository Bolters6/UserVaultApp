package progetto.vault.uservault.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "Vault")
@Table(name = "vault")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vault {
	
	@Id
	@SequenceGenerator(name = "vault_sequence", sequenceName = "vault_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vault_sequence")
	@Column(name="id", updatable = false)
	private Long idv;
	
	@Column(name="denaro", nullable = true)
	private Integer denaro = 0;
	
	@Column(name="souls", nullable=true)
	private Integer souls;
	
	@Column(name="bless", nullable=true)
	private Integer bless;
	
	@Column(name="wings", nullable=true)
	private String wings;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="sword_vault_rel",
				joinColumns = @JoinColumn(name = "vault_id", nullable = false),
				 inverseJoinColumns = @JoinColumn(name = "sword_id",nullable = false))
	private List<Sword> swords = new ArrayList<>();

	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(
			name = "potion_vault_rel",
			joinColumns = @JoinColumn(name = "vault_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name="potion_id", nullable = false)
	)
	private List<Potion> potions = new ArrayList<>();
}
