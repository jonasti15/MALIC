package com.malic.muskerrest.security;

import com.malic.muskerrest.filter.CustomAuthenticationFilter;
import com.malic.muskerrest.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final static int REMEMBER_ME_TIME = 86400;  //1 day

    // Cualquier usuario
    private final static String [] ANY_USER_MATCHERS = {
        "/images/**", "/news/all", "/estancias/shelter", "/especies/**", "/consejos/especie/**", "/avistamientos/add", "/user/add", "/rabbit/**"
    };

    // Usuarios que estan autenticados
    private final static String[] AUTHENTICATED_MATCHERS = {
            "/visitas/all", "/visitas/visita/**", "/user/username/**", "/user/user/**", "/reservas/user", "/reservas/count/**",
            "/reservas/add", "/reservas/delete", "/reservas/reserva/**","/animals/**"
    };

    // Usuarios con rol de ADMIN o WORKER
    private final static String [] ROLE_WORKER_ADMIN_MATCHERS = {
            "/visitas/**", "/reservas/**", "/estancias/**",
            "/tipoestado/**"
    };
    // Usuarios con rol de ADMIN o WORKER
    private final static String [] ROLE_NODE_MATCHERS = {
            "/constantes/post"
    };



    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(ANY_USER_MATCHERS).permitAll();
        http.authorizeRequests().antMatchers(AUTHENTICATED_MATCHERS).authenticated();
        http.authorizeRequests().antMatchers(ROLE_WORKER_ADMIN_MATCHERS).hasAnyAuthority("ROLE_WORKER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(ROLE_NODE_MATCHERS).hasAuthority("ROLE_NODE");
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
