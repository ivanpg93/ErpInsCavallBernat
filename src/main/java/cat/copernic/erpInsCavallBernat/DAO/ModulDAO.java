/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.DAO;

import cat.copernic.erpInsCavallBernat.model.Modul;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author adria
 */
public interface ModulDAO extends CrudRepository<Modul, Long> {

    Modul findByNom(String nom);

}
