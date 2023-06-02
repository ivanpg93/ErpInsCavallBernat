/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.serveis;


import cat.copernic.erpInsCavallBernat.model.Unitat;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */
public interface UnitatServiceInterface {
   
    public List<Unitat> llistarUnitats(); //Mètode que implementarem per llistar categories
    
    public String getRolUserCurrent(User username);
    
    public void crearUnitat(Unitat unitat); //Mètode que implementarem per afegir una categoria
    
    public void eliminarUnitat(Unitat unitat); //Mètode que implementarem per eliminar una categoria
    
    public Unitat cercarUnitat(Unitat unitat); //Mètode que implementarem per cercar una categoria
}
