/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Modul;
import cat.copernic.erpInsCavallBernat.model.Unitat;
import cat.copernic.erpInsCavallBernat.serveis.ModulServiceInterface;
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
public class ControladorModul {

    @Autowired
    private ModulServiceInterface modulService;

    @GetMapping("/moduls") //Pàgina productes de l'aplicació localhost:5050
    public String moduls(Model model, Modul id_modul, @AuthenticationPrincipal User username) {
        log.info("L'usuari autenticat és: " + username);
        var moduls = modulService.llistarModuls();
        var rol = modulService.getRolUserCurrent(username);
        log.info("ROL és: " + rol);

        model.addAttribute("moduls", moduls);
        model.addAttribute("rol", rol);

        return "moduls";
    }

    @GetMapping("/crearModul") //URL a la pàgina amb el formulari de les dades del producte
    public String crearModul(Model model, @AuthenticationPrincipal User username, Modul modul) {

        var rol = modulService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "crearModul"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @PostMapping("/guardarModul") //action = guardarProveidor
    public String guardarModul(@Valid Modul modul, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearModul";
        }
        modulService.crearModul(modul);
        return "redirect:/moduls";
    }

    @GetMapping("/eliminarModul/{id_modul}")
    public String eliminarModul(Modul modul) {
        modulService.eliminarModul(modul);
        return "redirect:/moduls";
    }

    @GetMapping("/editarModul/{id_modul}")
    public String editar(Model model, @AuthenticationPrincipal User username, Modul modul) {

        log.info(String.valueOf(modul.getId_modul()));
        modul = modulService.cercarModul(modul);
        model.addAttribute("modul", modul);

        var rol = modulService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "editarModul";
    }

}