package progetto.vault.uservault.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "Sword")
@Table(name="sword")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sword {

	@Id
	@SequenceGenerator(name = "sword_sequence", sequenceName = "sword_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sword_sequence")
	@Column(name="id", updatable = false)
	private Long ids;

	@Column(name="sword_name", nullable = false, columnDefinition = "TEXT")
	private String swordName;

	@Column(name="metallo", nullable = false, columnDefinition = "TEXT")
	private String metallo;
	
	@Column(name="tipo_spada", nullable = false, columnDefinition = "TEXT")
	private String tipoSpada;
	
	@Column(name="elemento", nullable = false, columnDefinition = "TEXT")
	private String elemento;

}
