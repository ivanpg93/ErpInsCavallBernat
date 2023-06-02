/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.DAO;


import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author adria
 */
public interface ComandaProfessorDAO extends CrudRepository<ComandaProfessor,Long> {
   ComandaProfessor findByNom(String nom);
  
}

