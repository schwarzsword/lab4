package config.security;


import config.security.handlers.SuccessLoginHandler;
import config.security.service.RestEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import service.AuthorizationService;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = {"repository", "impl", "config.database.persistence", "config.security.service", "config.security.handlers"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final
    UserDetailsService userDetailsService;

    final
    AuthorizationService authorizationService;

    public final DataSource dataSource;

    final
    RestEntryPoint restEntriyPoint;

    final
    SuccessLoginHandler successLoginHandler;


    @Autowired
    public SecurityConfig(
            @Qualifier("userDetailsService") UserDetailsService userDetailsService,
            AuthorizationService authorizationService,
            DataSource dataSource,
            RestEntryPoint restEntriyPoint,
            SuccessLoginHandler successLoginHandler
    ) {
        this.userDetailsService = userDetailsService;
        this.authorizationService = authorizationService;
        this.dataSource = dataSource;
        this.restEntriyPoint = restEntriyPoint;
        this.successLoginHandler = successLoginHandler;
    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .addFilterBefore(new CorsFilter(corsConfigurationSource()), UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/**"));

        http
                .authorizeRequests()
                .antMatchers("/login", "/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .formLogin()
                .successHandler(successLoginHandler)
                .failureHandler(failureHandler())
                .and().logout().logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restEntriyPoint)
                .and()
                .authenticationProvider(authenticationProvider())

                .rememberMe()
                .rememberMeCookieName("remember-me")
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .key("test")
                .tokenValiditySeconds(60 * 30)
                .alwaysRemember(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.addAllowedHeader("Access-Control-Allow-Origin: *");
        corsConfiguration.addAllowedHeader("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE, OPTIONS");
        corsConfiguration.addAllowedHeader("Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token");

        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

}
