/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.DAO;

import cat.copernic.erpInsCavallBernat.model.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ivan
 */
public interface UsuariDAO extends JpaRepository<Usuari,Long>{ 
    
    /*Mètode que retornarà l'usuari que passem per paràmetre. 
    *El nom d'aquest mètode ha de ser findByUsername, ja que és el que reconeix Spring Boot
    *com a mètode de seguretat per recuperar l'usuari.
    */
    Usuari findByUsername(String username);
    
}
