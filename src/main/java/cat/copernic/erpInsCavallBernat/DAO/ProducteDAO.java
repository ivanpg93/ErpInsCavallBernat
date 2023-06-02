/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.DAO;

import cat.copernic.erpInsCavallBernat.model.Producte;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ivan
 */
public interface ProducteDAO extends CrudRepository<Producte,Long> {
   Producte findByNom(String nom);
  
}
