package progetto.vault.uservault.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthFilter customAuthFilter = new CustomAuthFilter(authenticationManagerBean());
        customAuthFilter.setFilterProcessesUrl("/app/user/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/app/user/login").permitAll();
        http.authorizeRequests().antMatchers("/app/user/register").permitAll();
        http.authorizeRequests().antMatchers("/app/user/refreshtoken").permitAll();
        http.authorizeRequests().antMatchers("/app/vault/**").hasAnyAuthority("User");
        http.authorizeRequests().antMatchers("/app/user/update/**").hasAnyAuthority("Admin");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthFilter);
        http.addFilterBefore(new CustomAutoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = "authmanager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

