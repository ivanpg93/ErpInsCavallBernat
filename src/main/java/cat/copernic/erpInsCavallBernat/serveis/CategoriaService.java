package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cat.copernic.erpInsCavallBernat.DAO.CategoriaDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service ("categoriaDetailsService")
@Slf4j
public class CategoriaService implements CategoriaServiceInterface{
    
    /*Quan treballem en la capa de servei amb classes de tipus DAO, com és el cas, estem
     *treballant amb transaccions SQL, és a dir, quan fem una consulta a la BBDD, si aquesta
     *ha estat un èxit, el sistema ha de fer un COMMIT, en cas contrari un ROLLBACK. Així doncs,
     *mitjançant anotacions, hem d'indicar al sistema de quin tipus de transacció és cadascun
     *dels mètodes per accedir a la BBDD que implementem en aquesta classe.    
    */
    
    /*Atribut que defineix un producteDAO. Mitjançant aquest atribut el control ja no 
     *accedirà directament a la capa de dades, si no que accedirà mitjançant la capa de servei.
    */
    @Autowired
    private CategoriaDAO categoria; 

    /*LListar categories de la taula categoria de la BBDD erp*/
    @Override
    /*La notació @Transactional fa referència a la classe Transactional de Spring Framework.
     *En aquest cas no hi haurà ni COMMITS, ni ROLLBACKS, ja que no modifiquem la informació
     *de la BBDD, per tant, utilitzarem aquesta notació passant-li com a paràmetre readOnly=true
     *perquè només hem de llegir de la BBDD.
    */    
    @Transactional(readOnly=true) 
    public List<Categoria> llistarCategories() {
        
        /*Cridem al mètode findAll() de CrudRepository perquè ens retorni el llistat de categories de la BBDD.
         *findAll() retorna un objecte, per tant hem de fer un cast perquè l'objecte sigui un List de categoria
        */
        return (List<Categoria>) categoria.findAll(); 
    }
    
    @Override
    public String getRolUserCurrent(User username) {
        return username.getAuthorities().toString().substring(1, username.getAuthorities().toString().length()-1);
    }

    /*Afegir la categoria passat per paràmetre a la taula producte de la BBDD erp*/
    @Override
    @Transactional
    public void crearCategoria(Categoria categoria) {
        
        /*Cridem al mètode save() de CrudRepository perquè afegeixi la categoria passat com a paràmetre,
         *a la taula categoria de la BBDD erp.
        */
        this.categoria.save(categoria); 
    }

    /*Eliminar la categoria passat per paràmetre de la taula producte de la BBDD erp*/
    @Override
    @Transactional //Igual que en el mètode afegirCategoria, modifiquem la informació de la BBDD
    public void eliminarCategoria(Categoria categoria) {
        
        /*Cridem al mètode delete() de CrudRepository perquè elimini la categoria passat com a paràmetre,
         *de la taula categoria de la BBDD erp.
        */
        this.categoria.delete(categoria);
        
    }

    /*Cercar la categoria passat per paràmetre en la taula categoria de la BBDD erp*/
    @Override
    @Transactional(readOnly=true) //Igual que en el mètode llistarCategories, no modifiquem la informació de la BBDD
    public Categoria cercarCategoria(Categoria categoria) {
        
        /*Cridem al mètode findById() de CrudRepository perquè ens retorni la categoria passada com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas la categoria.
         *
         *Si la categoria no existei retornarà null (orElse(null)).
        */ 

        return this.categoria.findById(categoria.getId_Categoria()).orElse(null);
        
    }
    
}