package com.devapp.devapp.security;



import com.devapp.devapp.entities.EmailType;
import com.devapp.devapp.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    //springboot n'instanssi pas Bcript par default crre un ben dans l'app
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin").roles("ADMIN","USER")
//                .and()
//        .withUser("user").password("{noop}user").roles("USER");
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        //ce formulere generer par spring desactiver le au phase front-end
        //http.formLogin();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**", "/user/register/**","/user/forgetpassword","/user/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/user/deleteUserById/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/user/updatepassword/**").hasAuthority(EmailType.PASSWORD.name());
        http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/user/confirmaccount/**").hasAuthority(EmailType.CONFIRAMTION.name());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
