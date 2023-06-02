/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.DAO;

import cat.copernic.erpInsCavallBernat.model.Proveidor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rpuig
 */
public interface ProveidorDAO extends JpaRepository<Proveidor,String>{
    Proveidor findByCif(String username);
}
