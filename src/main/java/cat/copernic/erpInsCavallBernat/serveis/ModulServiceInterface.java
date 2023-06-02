/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Modul;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */
public interface ModulServiceInterface {
    
 public List<Modul> llistarModuls(); //Mètode que implementarem per llistar categories
    
    public String getRolUserCurrent(User username);
    
    public void crearModul(Modul modul); //Mètode que implementarem per afegir una categoria
    
    public void eliminarModul(Modul modul); //Mètode que implementarem per eliminar una categoria
    
    public Modul cercarModul(Modul modul); //Mètode que implementarem per cercar una categoria
}

