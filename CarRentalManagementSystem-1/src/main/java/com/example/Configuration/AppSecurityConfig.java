package com.example.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@Service
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String[] AUTH_WHITELIST = { // -- Swagger UI v2
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**", "/swagger-ui/**"
			// other public endpoints of your API may be appended to this array
	};

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService); // //
		// .setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		
		//"/cars/** ","/customers/**", "/rentalBookings/**"
		http.authorizeRequests().antMatchers("/signup**", "/login", "/users/**").permitAll().and().authorizeRequests()
			//	.antMatchers(HttpMethod.GET, "/car/**").permitAll().and().authorizeRequests()
			//	.antMatchers(HttpMethod.POST, "/car/**").hasAnyAuthority("Admin")
				 .antMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("Admin")
				// .antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("Admin")
				// .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.DELETE, "/car/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.GET, "/car/**").hasAnyAuthority("Admin")
		//		.antMatchers(HttpMethod.GET, "/rentalBooking**").hasAnyAuthority("Admin")

				.antMatchers(HttpMethod.DELETE, "/customer/**").hasAnyAuthority("Admin")
				.antMatchers(HttpMethod.POST, "/customer").hasAnyAuthority("Admin").anyRequest().authenticated().and()
				.formLogin().permitAll().and().httpBasic();
	}
}
