package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private	UserDetailsServiceImplementacao userDetailsServiceImplementacao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/logar").permitAll()
		.antMatchers(HttpMethod.GET, "/gerente/gerencia").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/gerente/adicionar").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/gerente/listar").permitAll()
		.antMatchers(HttpMethod.GET, "/gerente/salvar").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/gerente/excluir/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/gerente/atualizar/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/cliente/cadastrar").permitAll()
		.antMatchers(HttpMethod.GET, "/cliente/salvar").permitAll()
		.antMatchers(HttpMethod.GET, "/carrinho/**").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/pedido/**").hasRole("USER")

		.anyRequest().permitAll()
		
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.defaultSuccessUrl("/")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/imagens/**");
	}

	
	
	
}
