package progetto.vault.uservault.models;

import java.time.LocalDate;

import java.time.Period;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Entity(name = "Utente")
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@Column(name = "id", updatable = false)
	private Long userid;

	@Column(name = "user_name", nullable = false, unique = true)
	@NotBlank(message = "Username Non Puo Essere vuoto")
	@Size(min = 5, max = 12, message = "Il nome Utente debe contenere tra 5 e 12 carattere")
	private String userName;

	@Column(name = "password", nullable = false)
	@NotBlank(message = "Password Non Puo Essere vuoto")
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Email con formato incorretto")
	private String email;
	
	@Column(name = "data_nascita", nullable = false)
	@DateTimeFormat
	private LocalDate dataNascita;
	
	@Transient
	private Integer eta;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vault_id")
	private Vault vault;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role_rel", joinColumns = @JoinColumn(name = "user_id", nullable = false),
				inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
	private List<Role> roles = new ArrayList<>();

	public Integer getEta() {
		return Period.between(dataNascita, LocalDate.now()).getYears();
	}

}
