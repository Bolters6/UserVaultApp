package progetto.vault.uservault.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Potion")
@Table(name="potion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Potion {
	
	@Id
	@SequenceGenerator(name = "potion_sequence", sequenceName = "potion_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "potion_sequence")
	@Column(name="id", updatable = false)
	private Long potionid;

	@Column(name="nome_pozione", nullable = false, columnDefinition = "TEXT")
	private String nomePozione;

	@Column(name="tipo_pozione", nullable = false, columnDefinition = "TEXT")
	private String tipoPozione;

	@Column(name="grandezza_pozione", nullable = false, columnDefinition = "TEXT")
	private String grandezzaPozione ;

}
	

