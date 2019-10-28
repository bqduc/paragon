package net.paragon.msp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by aLeXcBa1990 on 24/11/2018.
 * 
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			// rest Login
			http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic().and().csrf()
					.disable();
		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// form login
		http.authorizeRequests()
		.antMatchers("/", "/login.xhtml", "/javax.faces.resource/**").permitAll()
		.anyRequest()
				.fullyAuthenticated()
				.and()
				.formLogin().loginPage("/login.xhtml")
				.defaultSuccessUrl("/index.xhtml")
				.failureUrl("/login.xhtml?authfailed=true").permitAll()
				.and()
				.logout().logoutSuccessUrl("/login.xhtml")
				.logoutUrl("/j_spring_security_logout")
				.and()
				.csrf().disable();

		// allow to use ressource links like pdf
		http.headers().frameOptions().sameOrigin();
	}
	*/

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    	.antMatchers(buildUnauthorizedMatchers()).permitAll()
      .anyRequest().authenticated()
    .and()
    .formLogin().loginPage("/login.xhtml")
    .defaultSuccessUrl("/")
    .permitAll()
    .and()
.logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/")
    .permitAll()
;
		http
    //.csrf().disable()
    .authorizeRequests().anyRequest().authenticated()
    .antMatchers(ConfigurationConstants.REST_API + "**").hasRole("CLIENT")
    .and()
    	.httpBasic().realmName(ConfigurationConstants.AUTHENTICATION_REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
    	
    	.and().csrf().disable().headers().frameOptions().disable()
    ;

  }

  @Bean
  public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
      return new CustomBasicAuthenticationEntryPoint();
  }

  /**
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    	.antMatchers(buildUnauthorizedMatchers()).permitAll()
    	//.antMatchers("/*", "/forum/**", "/bootstrap/**", "/dist/**", "/js/**", "/plugins/**", "/bower_components/**", "/pages/**").permitAll()
      .anyRequest().authenticated()
    .and()
    .formLogin()
    .failureUrl("/login?error")
    .loginPage("/login")
    .failureHandler(loginFailureHandler)
    .successHandler(loginSuccessfulHandler)
    
    .defaultSuccessUrl("/")
    .permitAll()
    .and()
.logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/login")
    .permitAll()
;

    loginSuccessfulHandler.setUseReferer(true);
 	
		http
    //.csrf().disable()
    .authorizeRequests().anyRequest().authenticated()
    .antMatchers(ConfigurationConstants.REST_API + "**").hasRole("CLIENT")
    .and()
    	.httpBasic().realmName(ConfigurationConstants.AUTHENTICATION_REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
    	
    	.and().csrf().disable().headers().frameOptions().disable()
    ;

  }
	 */	
	private String[] buildUnauthorizedMatchers() {
		String[] unauthorizedPatterns = new String[] { 
				"/*", 
				"/public/**", 
				"/resources/**", 
				"/includes/**", 
				"/pages/**", 
				"/auth/register/**",
				"/login.xhtml", 
				"/javax.faces.resource/**"
		};
		return unauthorizedPatterns;
	}
}