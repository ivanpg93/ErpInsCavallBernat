package cat.copernic.erpInsCavallBernat.controlador;

import cat.copernic.erpInsCavallBernat.model.Producte;
import cat.copernic.erpInsCavallBernat.serveis.CategoriaServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.ProducteServiceInterface;
import cat.copernic.erpInsCavallBernat.serveis.ProveidorServiceInterface;
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
 * @author ivan
 */
@Controller
@Slf4j
public class ControladorProducte {

    @Autowired
    private ProducteServiceInterface producteService;

    //Need proveidors for createProducte foreign key
    @Autowired
    private ProveidorServiceInterface proveidorService;
    @Autowired
    private CategoriaServiceInterface categoriaService;
     @Autowired
    private UnitatServiceInterface unitatService;

    @GetMapping("/productes") //Pàgina productes de l'aplicació localhost:5050
    public String productes(Model model, @AuthenticationPrincipal Producte id_Producte, @AuthenticationPrincipal User username) {

        var productes = producteService.llistarProductes();
        var rol = producteService.getRolUserCurrent(username);

        model.addAttribute("productes", productes);
        model.addAttribute("rol", rol);

        var proveidors = proveidorService.llistarProveidors();
        var categories = categoriaService.llistarCategories();
        var unitats = unitatService.llistarUnitats();
        model.addAttribute("proveidors", proveidors);
        model.addAttribute("categories", categories);
        model.addAttribute("unitats", unitats);

        return "productes";
    }

    @GetMapping("/crearProducte") //URL a la pàgina amb el formulari de les dades del producte
    public String crearProducte(Model model, @AuthenticationPrincipal User username, Producte producte) {
        var proveidors = proveidorService.llistarProveidors();
        var categories = categoriaService.llistarCategories();
        var unitats = unitatService.llistarUnitats();
        model.addAttribute("proveidors", proveidors);
        model.addAttribute("categories", categories);
        model.addAttribute("unitats", unitats);
        var rol = proveidorService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);
        return "crearProducte"; //Retorna la pàgina on es mostrarà el formulari de les dades dels productes
    }

    @PostMapping("/guardarProducte") //action = guardarProveidor
    public String guardarProducte(@Valid Producte producte, Errors errors) {
        if (errors.hasErrors()) {
            log.info("S'ha produït un error");
            return "crearProducte";
        }
        producteService.crearProducte(producte);
        return "redirect:/productes";
    }

    @GetMapping("/eliminarProducte/{id_Producte}")
    public String eliminarProducte(Producte producte) {
        producteService.eliminarProducte(producte);
        return "redirect:/productes";
    }

    @GetMapping("/editarProducte/{id_Producte}")
    public String editar(Model model, @AuthenticationPrincipal User username, Producte producte) {

        log.info(String.valueOf(producte.getId_Producte()));
        producte = producteService.cercarProducte(producte);
        model.addAttribute("producte", producte);

        var proveidors = proveidorService.llistarProveidors();
        var categories = categoriaService.llistarCategories();
        var unitats = unitatService.llistarUnitats();
        model.addAttribute("proveidors", proveidors);
        model.addAttribute("categories", categories);
        model.addAttribute("unitats", unitats);

        var rol = proveidorService.getRolUserCurrent(username);
        model.addAttribute("rol", rol);

        return "editarProducte";
    }

}
