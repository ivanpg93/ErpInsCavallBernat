/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Categoria;
import cat.copernic.erpInsCavallBernat.serveis.CategoriaServiceInterface;
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
public class ControladorCategoria {

    @Autowired
    private CategoriaServiceInterface categoriaService;

    @GetMapping("/categories") //Pàgina productes de l'aplicació localhost:5050
    public String categories(Model model, Categoria id_Categoria, @AuthenticationPrincipal User username) {
        log.info("L'usuari autenticat és: " + username);
        var categories = categoriaService.llistarCategories();
        var rol = categoriaService.getRolUserCurrent(username);
        log.info("ROL és: " + rol);

        model.addAttribute("categories", categories);
        model.addAttribute("rol", rol);

        return "categories";
    }

    @GetMapping("/crearCategoria") //URL a la pàgina amb el formulari de les dades del producte
    public String crearCategoria(Model model, @AuthenticationPrincipal User username, Categoria categoria) {

        var rol = categoriaService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "crearCategoria"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @PostMapping("/guardarCategoria") //action = guardarProveidor
    public String guardarCategoria(@Valid Categoria categoria, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearCategoria";
        }
        categoriaService.crearCategoria(categoria);
        return "redirect:/categories";
    }

    @GetMapping("/eliminarCategoria/{id_Categoria}")
    public String eliminarCategoria(Categoria categoria) {
        categoriaService.eliminarCategoria(categoria);
        return "redirect:/categories";
    }

    @GetMapping("/editarCategoria/{id_Categoria}")
    public String editar(Model model, @AuthenticationPrincipal User username, Categoria categoria) {

        log.info(String.valueOf(categoria.getId_Categoria()));
        categoria = categoriaService.cercarCategoria(categoria);
        model.addAttribute("categoria", categoria);

        var rol = categoriaService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "editarCategoria";
    }

}
