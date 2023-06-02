/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.DAO.UnitatDAO;
import cat.copernic.erpInsCavallBernat.model.Unitat;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adria
 */

@Service ("unitatDetailsService")
@Slf4j
public class UnitatService implements UnitatServiceInterface{
    
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
    private UnitatDAO unitat; 

    /*LListar categories de la taula categoria de la BBDD erp*/
    @Override
    /*La notació @Transactional fa referència a la classe Transactional de Spring Framework.
     *En aquest cas no hi haurà ni COMMITS, ni ROLLBACKS, ja que no modifiquem la informació
     *de la BBDD, per tant, utilitzarem aquesta notació passant-li com a paràmetre readOnly=true
     *perquè només hem de llegir de la BBDD.
    */    
    @Transactional(readOnly=true) 
    public List<Unitat> llistarUnitats() {
        
        /*Cridem al mètode findAll() de CrudRepository perquè ens retorni el llistat de categories de la BBDD.
         *findAll() retorna un objecte, per tant hem de fer un cast perquè l'objecte sigui un List de categoria
        */
        return (List<Unitat>) unitat.findAll(); 
    }
    
    @Override
    public String getRolUserCurrent(User username) {
        return username.getAuthorities().toString().substring(1, username.getAuthorities().toString().length()-1);
    }

    /*Afegir la categoria passat per paràmetre a la taula producte de la BBDD erp*/
    @Override
    @Transactional
    public void crearUnitat(Unitat unitat) {
        
        /*Cridem al mètode save() de CrudRepository perquè afegeixi la categoria passat com a paràmetre,
         *a la taula categoria de la BBDD erp.
        */
        this.unitat.save(unitat); 
    }

    /*Eliminar la categoria passat per paràmetre de la taula producte de la BBDD erp*/
    @Override
    @Transactional //Igual que en el mètode afegirCategoria, modifiquem la informació de la BBDD
    public void eliminarUnitat(Unitat unitat) {
        
        /*Cridem al mètode delete() de CrudRepository perquè elimini la categoria passat com a paràmetre,
         *de la taula categoria de la BBDD erp.
        */
        this.unitat.delete(unitat);
        
    }

    /*Cercar la categoria passat per paràmetre en la taula categoria de la BBDD erp*/
    @Override
    @Transactional(readOnly=true) //Igual que en el mètode llistarCategories, no modifiquem la informació de la BBDD
    public Unitat cercarUnitat(Unitat unitat) {
        
        /*Cridem al mètode findById() de CrudRepository perquè ens retorni la categoria passada com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas la categoria.
         *
         *Si la categoria no existei retornarà null (orElse(null)).
        */ 

        return this.unitat.findById(unitat.getId_unitat()).orElse(null);
        
    }
    
}