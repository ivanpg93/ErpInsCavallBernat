package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Proveidor;
import cat.copernic.erpInsCavallBernat.serveis.ProveidorServiceInterface;
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
public class ControladorProveidor {

    @Autowired
    private ProveidorServiceInterface proveidorService;

    @GetMapping("/proveidors") //Pàgina proveidors de l'aplicació localhost:8080
    public String proveidors(Model model, @AuthenticationPrincipal User username, Proveidor cif) {

        var proveidors = proveidorService.llistarProveidors();
        var rol = proveidorService.getRolUserCurrent(username);

        model.addAttribute("proveidors", proveidors);
        model.addAttribute("rol", rol);

        return "proveidors";
    }

    @GetMapping("/crearProveidor") //URL a la pàgina amb el formulari de les dades del proveidor
    public String crearProveidor(Model model, @AuthenticationPrincipal User username, Proveidor proveidor) {

        var rol = proveidorService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "crearProveidor"; //Retorna la pàgina on es mostrarà el formulari de les dades dels proveidors
    }

    @PostMapping("/guardarProveidor") //action = guardarProveidor
    public String guardarProveidor(@Valid Proveidor proveidor, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearProveidor";
        }
        proveidorService.crearProveidor(proveidor);
        return "redirect:/proveidors";
    }

    @GetMapping("/eliminarProveidor/{cif}")
    public String eliminarProveidor(Proveidor proveidor) {
        proveidorService.eliminarProveidor(proveidor);
        return "redirect:/proveidors";
    }

    @GetMapping("/editarProveidor/{cif}")
    public String editar(Model model, @AuthenticationPrincipal User username, Proveidor proveidor) {

        log.info(String.valueOf(proveidor.getCif()));
        proveidor = proveidorService.cercarProveidor(proveidor);
        model.addAttribute("proveidor", proveidor);

        var rol = proveidorService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "editarProveidor";
    }

}
