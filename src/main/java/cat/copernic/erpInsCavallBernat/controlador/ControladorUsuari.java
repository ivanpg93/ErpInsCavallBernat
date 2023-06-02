package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Rol;
import cat.copernic.erpInsCavallBernat.model.Usuari;
import cat.copernic.erpInsCavallBernat.serveis.RolServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.UsuariServiceInterface;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ivan
 */

@Controller
@Slf4j
public class ControladorUsuari {
    
    @Autowired
    private UsuariServiceInterface usuariService;
    
//    @Autowired
//    private RolServiceInterface rolService;
    
    @GetMapping("/usuaris") //Pàgina usuaris de l'aplicació localhost:5050
    public String usuaris(Model model, @AuthenticationPrincipal User username) {
        
        var usuaris = usuariService.llistarUsuaris();
        log.info("LISTA DE USUARIOS::: " + usuaris.toString());
        var rol = usuariService.getRolUserCurrent(username);
        var email = "";
        //var cercarUsername = usuariDAO.findByUsername(email);

        model.addAttribute("usuaris", usuaris);
        model.addAttribute("rol", rol);
        
        model.addAttribute("email", email);
        //model.addAttribute("cercarUsername", cercarUsername);
        
        return "usuaris";
    }

    /*Definim el mètode per mostrar la pàgina amb el forumlari de les dades del usuari passat com a paràmetre.
     *Aquest usuari, si no èxistei, es crearà de manera automàtica en el moment que executem aquest mètode amb els
     *atributs buits (recordem que el constructor construeix un objecte buit).
     */
    @GetMapping("/crearUsuari") //URL a la pàgina amb el formulari de les dades del usuari
    public String crearUsuari(Model model, @AuthenticationPrincipal User username, Usuari usuari) {
        var rol = usuariService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);
        
        return "crearUsuari"; //Retorna la pàgina on es mostrarà el formulari de les dades dels usuaris
    }

    /*Definim el mètode per assignar els valors introduïts en el formulari, a l'objecte usuari
     *passat com a paràmetre en el mètode dadesUsuari. Això ho fem mitjançant l'anotació @PostMapping,
     *ja que el mètode que fem servir per enviar les dades és el post. Com a paràmetre hem de passar
     *el valor de l'action del formulari, d'aquesta manera el sistema identifica el mètode al qual ha
     *d'enviar les dades introduïdes mitjançant el formulari.
     *
     *A part d'assignar les dades a usuari mitjançant @PostMapping, en aquest cas utilitzarem el mètode
     *crearUsuari de la classe UsuariService, per guardar el usuari en la base de dades i finalment retornar
     *a la pàgina d'inici.
     */
 /*Abans de guardar les dades del usuari, és quan comprovem si són valides o no, perquè el sistema
     *realitzi aquesta validació, utilitzem l'anotació @Valid precedint a l'objecte al qual pertanyen
     *els valors a validar, en el nostre cas, l'objecte Usuari passat per paràmetre.
     *Per un altre costat, al mètode li passem el paràmetre error, objectede la classe Errors per saber
     *si el formulari té errors.
     */
    @PostMapping("/guardarUsuari") //action = guardarUsuari
    public String guardarUsuari(@Valid Usuari usuari, /*Rol rol,*/ Errors errors) {

        if (errors.hasErrors()) { //Si s'han produït errors...
            log.info("S'ha produït un error");
            return "crearUsuari"; //Mostrem la pàgina del formulari
        }

        usuariService.crearUsuari(usuari); //Afegim el usuari passat per paràmetre a la base de dades
       // rolService.crearRol(rol);
        return "redirect:/usuaris"; //Retornem a la pàgina inici mitjançant redirect
    }

    /*Definim el mètode que ens retornarà la pàgina formulariUsuari on se'ns mostraran les dades del usuari
     *amb l'id_usuari de l'URL que li passem a @GetMapping com a paràmetre, ja que en aquest cas el usuari 
     *existeix.
     *El sistema Spring associa l'id_usuari passat com a paràmetre en @GetMapping al usuari 
     *passat com a paràmetre en el mètode editar i crida automàticament al mètode setId_usuari 
     *de la classe Usuari per fer aquesta associació, és a dir, el que fa és usuari.setId_usuari(id_usuari).
     *IMPORTANT: id_usuari ha de tenir el mateix nom que l'atribut id de la classe Usuari.
     */
    @GetMapping("/editar/{id_usuari}")
    public String editar(Model model, @AuthenticationPrincipal User username, Usuari usuari) {

        log.info(String.valueOf(usuari.getId_usuari())); //Mostra id_usuari de Usuari

        /*Cerquem l'usuari passat per paràmetre amb l'id_usuari de @GetMapping mitjançant 
         *el mètode cercarUsuari de la capa de servei.*/
        usuari = usuariService.cercarUsuari(usuari);

        model.addAttribute("usuari", usuari); //Enviem les dades del gos resultant de la cerca a la pàgina formulariUsuari
        
        var rol = usuariService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "editarUsuari"; //Retorna la pàgina amb el formulari de les dades del usuari
    }

    /*Definim el mètode per guardar l'usuari en la base de dades i finalment retornar
     *a la pàgina d'inici. L'usuari l'eliminarem mitjançant el mètode eliminarUsuari de
     *la classe UsuariService.
     *El sistema per associar l'id del usuari a l'objecte usuari passat per paràmetre, és el mateix
     *que el del mètode editar.
     *IMPORTANT: id_usuari ha de tenir el mateix nom que l'atribut id de la classe Usuari.
     */
    @GetMapping("/eliminar/{id_usuari}")
    public String eliminar(Usuari usuari/*, Rol rol*/) {

        /*Eliminem el usuari passat per paràmetre amb l'id_usuari de @GetMapping mitjançant 
         *el mètode eliminarUsuari de la capa de servei.*/
        usuariService.eliminarUsuari(usuari);
      //  rolService.eliminarRol(rol);

        return "redirect:/usuaris"; //Retornem a la pàgina inici mitjançant redirect
    }
    
}
