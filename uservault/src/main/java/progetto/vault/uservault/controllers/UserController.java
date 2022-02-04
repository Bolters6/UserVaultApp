package progetto.vault.uservault.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progetto.vault.uservault.models.Role;
import progetto.vault.uservault.models.Utente;
import progetto.vault.uservault.models.Vault;
import progetto.vault.uservault.services.servicesinterface.RoleService;
import progetto.vault.uservault.services.servicesinterface.UserService;
import progetto.vault.uservault.services.servicesinterface.VaultService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path="app/user")
public class UserController {

	private final UserService userService;
	private final VaultService vaultService;
	private final RoleService roleService;

	@PostMapping(path = "/register")
	public ResponseEntity<?> registerUser(@RequestBody @Valid Utente user) {
		user.setRoles(List.of(roleService.getRole(("User"))));
		userService.registerUser(user);
		Vault vault = new Vault();
		vaultService.createVault(vault);
		userService.addVaultUser(user.getUserName(), vault.getIdv());
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/update")
	public ResponseEntity<Utente> updateUser(@RequestBody @Valid Utente user) {
		return ResponseEntity.ok().body(userService.updateUser(user));
	}

	@GetMapping(path = "/refreshtoken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("chidolon".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				Utente user = userService.getUser(username);
				String accessJToken = JWT.create().withSubject(user.getUserName())
						.withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
						.sign(algorithm);
				HashMap<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessJToken);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);

			} catch (Exception exception) {
				response.setHeader("error", exception.getMessage());
				response.sendError(FORBIDDEN.value(), exception.getMessage());
			}
		}else{
			throw new RuntimeException("refresh token non esiste");
		}
	}
}
