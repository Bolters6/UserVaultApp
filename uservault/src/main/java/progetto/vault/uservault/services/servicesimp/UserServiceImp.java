package progetto.vault.uservault.services.servicesimp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progetto.vault.uservault.models.Utente;
import progetto.vault.uservault.repository.UserRepository;
import progetto.vault.uservault.repository.VaultRepository;
import progetto.vault.uservault.services.servicesinterface.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService, UserDetailsService{

	private final UserRepository userRepo;
	private final VaultRepository vaultRepository;
	private final PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!userRepo.existsByUserName(username)) {
			throw new UsernameNotFoundException("User non Esistente nel Database");
		}
		log.info("User trovato");
		Utente user = userRepo.findByUserName(username);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		return new User(user.getUserName(), user.getPassword(), authorities);
	}

	@Override
	public Utente registerUser(Utente user) {
		if(userRepo.existsByUserName(user.getUserName())){
			throw new IllegalStateException("User Esistente");
		}else if(userRepo.existsByEmail(user.getEmail())){
			throw new IllegalStateException("Email Esistente");
		}
		log.info("Usuario creato con esito");
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Utente updateUser(Utente user){
		if(userRepo.existsById(user.getUserid())){
			log.info("Vault updated con esito");
			return userRepo.save(user);
		}
		throw new IllegalStateException("User non esistente");
	}

	@Override
	public Utente getUser(String username) {
		if(userRepo.existsByUserName(username)){
			return userRepo.findByUserName(username);
		}
		throw new IllegalStateException("User Esistente");
	}

	@Override
	public void addVaultUser(String username, Long id) {
		if(userRepo.existsByUserName(username) && vaultRepository.existsById(id)){
			userRepo.findByUserName(username).setVault(vaultRepository.findByIdv(id));
			log.info("Vault aggiunto con esito");
			return;
		}
		throw new IllegalStateException("vault o utente non esistente");
	}
}
