
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cat.copernic.erpInsCavallBernat.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ivan
 */
@Configuration //Indica al sistema que és una classe de configuració
@EnableWebSecurity //Habilita la seguretat web
public class ConfiguracioAutenticacio extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; //Objecte per recuperar l'usuari

    /*Injectem mitjançant @Autowired, els mètodes de la classe AuthenticationManagerBuilder. Mitjançant
     *aquesta classe cridarem al mètode loadUserByUsername de la classe UsuariServicenquè és el mètode que
     *realment fa l'autenticació.
     *Cridem al mètode passwordEncoder passant-li com a paràmetre un objecte de tipus BCryptPasswordEncoder()
     *per encriptar el password introduït per l'usuari en el moment d'autenticar-se i comparar-lo
     *amb l'encriptació del password guardat a la BBDD corresponent a l'username introduït en l'autenticació.
     */
    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
     /*Passem com a paràmetre un objecte de la classe HttpSecurity de Spring Security que
     *és el que ens permetrà cridar als mètodes per configurar les restriccions de les URL.*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/productes", "/crear/**", "/editar/**") //URL i subURLS (**) on pot accedir...
                .hasAuthority("Administrador") //...l'usuari amb rol administrador
                .anyRequest() //Qualsevol direcció que no sigui /login
                .authenticated() //Obliga a estar autenticat per a direccionar a la resta de direccions
                .and()
                .formLogin() //Objecte que representa el formulari de login personalitzat que utilitzarem
                .loginPage("/login") //Pàgina on es troba el formulari per fer login personalitzat
                .defaultSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/errors/error403") //Mostrarem la pàgina error403 si l'usuari no té accés a una àgina o acció 
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
}

