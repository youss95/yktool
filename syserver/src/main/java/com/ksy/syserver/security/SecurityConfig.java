package com.ksy.syserver.security;

import static com.ksy.syserver.security.SecurityConstants.SIGN_UP_URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ksy.syserver.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
		)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationEntryPoint unauthrizedHandler; //유저가 아이디 비번 맞는지 확인한걸 authen 메니저에게 패스
	
	@Autowired
	private CustomUserDetailService CustomUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManageBuilder) throws Exception {
		
		authenticationManageBuilder.userDetailsService(CustomUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	//http sec
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable() //토큰을 사용하기 때문에
		.exceptionHandling().authenticationEntryPoint(unauthrizedHandler).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //인증되지 않은 사용자가 나타났을때 나오게 될, 토큰을 사용하는 1번째 이유 : stateless
		.and().authorizeRequests().antMatchers( "/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js").permitAll()
		.antMatchers(SIGN_UP_URLS).permitAll()
		.anyRequest().authenticated();
		
		

	}
	
}
