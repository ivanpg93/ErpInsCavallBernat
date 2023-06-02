package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Categoria;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */

//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface CategoriaServiceInterface {
    
    public List<Categoria> llistarCategories(); //Mètode que implementarem per llistar categories
    
    public String getRolUserCurrent(User username);
    
    public void crearCategoria(Categoria categoria); //Mètode que implementarem per afegir una categoria
    
    public void eliminarCategoria(Categoria categoria); //Mètode que implementarem per eliminar una categoria
    
    public Categoria cercarCategoria(Categoria categoria); //Mètode que implementarem per cercar una categoria
}
