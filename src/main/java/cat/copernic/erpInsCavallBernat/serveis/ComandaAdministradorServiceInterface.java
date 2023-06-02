package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.ComandaAdministrador;

import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author ivan
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface ComandaAdministradorServiceInterface {
    
    public List<ComandaAdministrador> llistarComandesAdministrador(); //Mètode que implementarem per llistar comandes
    
    public String getRolUserCurrent(User username);
   
    public void crearComandaAdministrador(ComandaAdministrador comandaAdministrador); //Mètode que implementarem per afegir una comanda
    
    public void eliminarComandaAdministrador(ComandaAdministrador comandaAdministrador); //Mètode que implementarem per eliminar una comanda
    
    public ComandaAdministrador cercarComandaAdministrador(ComandaAdministrador comandaAdministrador); //Mètode que implementarem per cercar una comanda

  
}