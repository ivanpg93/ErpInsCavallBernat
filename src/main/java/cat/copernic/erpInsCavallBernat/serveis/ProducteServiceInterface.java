package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Producte;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface ProducteServiceInterface {
    
    public List<Producte> llistarProductes(); //Mètode que implementarem per llistar productes
    
    public String getRolUserCurrent(User username);
    
    public void crearProducte(Producte producte); //Mètode que implementarem per afegir un gos
    
    public void eliminarProducte(Producte producte); //Mètode que implementarem per eliminar un producte
    
    public Producte cercarProducte(Producte producte); //Mètode que implementarem per cercar un producte
}
