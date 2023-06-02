/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.DAO.LineaComandaDAO;
import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.model.LineaComanda;
import java.util.ArrayList;
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
/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service ("lineaComandaDetailsService")
@Slf4j
public class LineaComandaService implements LineaComandaServiceInterface{
    
    /*Quan treballem en la capa de servei amb classes de tipus DAO, com és el cas, estem
     *treballant amb transaccions SQL, és a dir, quan fem una consulta a la BBDD, si aquesta
     *ha estat un èxit, el sistema ha de fer un COMMIT, en cas contrari un ROLLBACK. Així doncs,
     *mitjançant anotacions, hem d'indicar al sistema de quin tipus de transacció és cadascun
     *dels mètodes per accedir a la BBDD que implementem en aquesta classe.    
    */
    
    /*Atribut que defineix un lineaComandaDAO. Mitjançant aquest atribut el control ja no 
     *accedirà directament a la capa de dades, si no que accedirà mitjançant la capa de servei.
    */
    @Autowired
    private LineaComandaDAO lineaComanda; 
   
  

    /*LListar les lineas comanda de la taula producte de la BBDD erp*/
    @Override
    /*La notació @Transactional fa referència a la classe Transactional de Spring Framework.
     *En aquest cas no hi haurà ni COMMITS, ni ROLLBACKS, ja que no modifiquem la informació
     *de la BBDD, per tant, utilitzarem aquesta notació passant-li com a paràmetre readOnly=true
     *perquè només hem de llegir de la BBDD.
    */    
    @Transactional(readOnly=true) 
    public List<LineaComanda> llistarLineaComanda() {
        
        /*Cridem al mètode findAll() de CrudRepository perquè ens retorni el llistat de lineas comandes de la BBDD.
         *findAll() retorna un objecte, per tant hem de fer un cast perquè l'objecte sigui un List de linia comanda
        */
        return (List<LineaComanda>) lineaComanda.findAll(); 
    }
    
    @Override
    public List<LineaComanda> llistarLineaComandaWhereComanda(ComandaProfessor cp) {
        List<LineaComanda> myList = (List<LineaComanda>)lineaComanda.findAll();
        List<LineaComanda> myFinalList = new ArrayList<>();
        
        for(LineaComanda lc: myList){
            if(lc.getId_comanda().getId_comanda() == cp.getId_comanda()){
                myFinalList.add(lc);
            }
        }
        
        return myFinalList;
    }
    
    
    @Override
    public String getRolUserCurrent(User username) {
        return username.getAuthorities().toString().substring(1, username.getAuthorities().toString().length()-1);
    }

    /*Afegir el producte passat per paràmetre a la taula linia comanda de la BBDD erp*/
    @Override
    @Transactional
    public void crearLineaComanda(LineaComanda lineaComanda) {
        
        /*Cridem al mètode save() de CrudRepository perquè afegeixi el producte passat com a paràmetre,
         *a la taula linia comanda de la BBDD erp.
        */
        this.lineaComanda.save(lineaComanda); 
    }

    /*Eliminar la linia comanda passat per paràmetre de la taula linia comanda de la BBDD erp*/
    @Override
    @Transactional //Igual que en el mètode afegirLiniaComanda, modifiquem la informació de la BBDD
    public void eliminarLineaComanda(LineaComanda lineaComanda) {
        
        /*Cridem al mètode delete() de CrudRepository perquè elimini la linia comanda passat com a paràmetre,
         *de la taula linia comanda de la BBDD erp.
        */
        this.lineaComanda.delete(lineaComanda);
        
        
    }

    /*Cercar la linia comanda passat per paràmetre en la taula linia comanda de la BBDD erp*/
    @Override
    @Transactional(readOnly=true) //Igual que en el mètode llistarLiniaComanda, no modifiquem la informació de la BBDD
    public LineaComanda cercarLineaComanda(LineaComanda lineaComanda) {
        
        /*Cridem al mètode findById() de CrudRepository perquè ens retorni la linia comanda passat com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas la linia comanda.
         *
         *Si la linia comanda no existei retornarà null (orElse(null)).
        */ 

          return this.lineaComanda.findById(lineaComanda.getId_linea_comanda()).orElse(null);
        
    }
     
   

    
}
