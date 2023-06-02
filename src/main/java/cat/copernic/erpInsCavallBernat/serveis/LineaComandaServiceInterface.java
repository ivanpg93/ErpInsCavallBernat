/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.model.LineaComanda;
import java.util.List;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */
//Interface on definirem els mètodes  personalitzats per la nostra aplicació
public interface LineaComandaServiceInterface {
    
    public List<LineaComanda> llistarLineaComanda(); //Mètode que implementarem per llistar les lineas comandes
    
    public List<LineaComanda> llistarLineaComandaWhereComanda(ComandaProfessor cp);
    
    public String getRolUserCurrent(User username);
   
    public void crearLineaComanda(LineaComanda lineaComanda); //Mètode que implementarem per afegir una linea comanda
    
    public void eliminarLineaComanda(LineaComanda lineaComanda); //Mètode que implementarem per eliminar una linea comanda
    
    public LineaComanda cercarLineaComanda(LineaComanda lineaComanda); //Mètode que implementarem per cercar una linea comanda
    
    

  
}
