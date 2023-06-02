/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.controlador;


import cat.copernic.erpInsCavallBernat.model.Unitat;
import cat.copernic.erpInsCavallBernat.serveis.UnitatServiceInterface;
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
 * @author adria
 */
@Controller
@Slf4j
public class ControladorUnitat {

    @Autowired
    private UnitatServiceInterface unitatService;

    @GetMapping("/unitats") //Pàgina productes de l'aplicació localhost:5050
    public String unitats(Model model, Unitat id_unitat, @AuthenticationPrincipal User username) {
        log.info("L'usuari autenticat és: " + username);
        var unitats = unitatService.llistarUnitats();
        var rol = unitatService.getRolUserCurrent(username);
        log.info("ROL és: " + rol);

        model.addAttribute("unitats", unitats);
        model.addAttribute("rol", rol);

        return "unitats";
    }

    @GetMapping("/crearUnitat") //URL a la pàgina amb el formulari de les dades del producte
    public String crearUnitat(Model model, @AuthenticationPrincipal User username, Unitat unitat) {

        var rol = unitatService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "crearUnitat"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @PostMapping("/guardarUnitat") //action = guardarProveidor
    public String guardarUnitat(@Valid Unitat unitat, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearUnitat";
        }
        unitatService.crearUnitat(unitat);
        return "redirect:/unitats";
    }

    @GetMapping("/eliminarUnitat/{id_unitat}")
    public String eliminarUnitat(Unitat unitat) {
        unitatService.eliminarUnitat(unitat);
        return "redirect:/unitats";
    }

    @GetMapping("/editarUnitat/{id_unitat}")
    public String editar(Model model, @AuthenticationPrincipal User username, Unitat unitat) {

        log.info(String.valueOf(unitat.getId_unitat()));
        unitat = unitatService.cercarUnitat(unitat);
        model.addAttribute("unitat", unitat);

        var rol = unitatService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "editarUnitat";
    }

}