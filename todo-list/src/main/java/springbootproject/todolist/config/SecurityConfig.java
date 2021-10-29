package springbootproject.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springbootproject.todolist.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserService userService;
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	}
	
	// add a reference to our security data source
	
	
	@Autowired
	private CustomAuthenticationSucsessHandler customAuthenticationSuccessHandler;
	
	
	/*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }*/
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		// use jdbc authentication ... oh yeah!!!		
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth= new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/").hasRole("MANAGER")
		.antMatchers("/").hasRole("ADMIN")
			.and()
			.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.successHandler(customAuthenticationSuccessHandler)
			.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling()
			.accessDeniedPage("/access-denied");
	}
		
	
	
}
