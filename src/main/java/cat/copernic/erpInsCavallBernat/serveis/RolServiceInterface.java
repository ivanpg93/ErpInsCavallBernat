/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Rol;
import cat.copernic.erpInsCavallBernat.model.Unitat;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */
public interface RolServiceInterface {
   
    public List<Rol> llistarRols(); //Mètode que implementarem per llistar rols
    
    public String getRolUserCurrent(User username);
    
    public void crearRol(Rol rol); //Mètode que implementarem per afegir una rol
    
    public void eliminarRol(Rol rol); //Mètode que implementarem per eliminar una rol
    
    public Rol cercarRol(Rol rol); //Mètode que implementarem per cercar una rol
}
