/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.ComandaAdministrador;
import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.serveis.ComandaAdministradorServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.ComandaProfessorServiceInterface;
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
public class ControladorComandaAdministrador {

    @Autowired
    private ComandaAdministradorServiceInterface comandaAdministradorService;

    @Autowired
    private ComandaProfessorServiceInterface comandaProfessorService;

    @GetMapping("/comandesAdministrador") //Pàgina productes de l'aplicació localhost:5050
    public String comandesAdministrador(Model model, @AuthenticationPrincipal User username, ComandaAdministrador id_comanda_centralitzada, ComandaProfessor id_comanda) {

        var comandesAdministrador = comandaAdministradorService.llistarComandesAdministrador();

        model.addAttribute("comandesAdministrador", comandesAdministrador);

        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();

        model.addAttribute("comandesProfessor", comandesProfessor);

        var rol = comandaProfessorService.getRolUserCurrent(username);

        model.addAttribute("rol", rol);

        return "comandesAdministrador";
    }

    @GetMapping("/crearComandaAdministrador") //URL a la pàgina amb el formulari de les dades del producte
    public String crearAdministrador(@AuthenticationPrincipal User username, ComandaAdministrador id_comanda_centralitzada, Model model) {
        var comandesProfessor = comandaProfessorService.llistarComandesProfessor();
        model.addAttribute("comandesProfessor", comandesProfessor);
        var rol = comandaProfessorService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);
        return "crearComandaAdministrador"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @PostMapping("/guardarComandaAdministrador") //action = guardarProveidor
    public String guardarComandaAdministrador(@Valid ComandaAdministrador id_comanda_centralitzada, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearComandaAdministrador";
        }
        comandaAdministradorService.crearComandaAdministrador(id_comanda_centralitzada);
        return "redirect:/comandesAdministrador";
    }

    @GetMapping("/eliminarComandaAdministrador/{id_ComandaAdministrador}")
    public String eliminarComandaAdministrador(ComandaAdministrador comandaAdministrador) {
        comandaAdministradorService.eliminarComandaAdministrador(comandaAdministrador);
        return "redirect:/comandesAdministrador";
    }

    @GetMapping("/editarComandaAdministrador/{id_ComandaAdministrador}")
    public String editarComandaAdministrador(ComandaAdministrador comandaAdministrador, Model model) {

        log.info(String.valueOf(comandaAdministrador.getId_comanda_centralitzada()));
        comandaAdministrador = comandaAdministradorService.cercarComandaAdministrador(comandaAdministrador);
        model.addAttribute("comandaAdministrador", comandaAdministrador);

        return "editarComandaAdministrador";
    }

    @GetMapping("/mesInfoComandaAdministrador/{id_ComandaAdministrador}")
    public String editar(ComandaAdministrador comandaAdministrador, Model model) {

        log.info(String.valueOf(comandaAdministrador.getId_comanda_centralitzada()));
        comandaAdministrador = comandaAdministradorService.cercarComandaAdministrador(comandaAdministrador);
        model.addAttribute("comandaAdministrador", comandaAdministrador);

        return "mesInfoComandaAdministrador";
    }

}
