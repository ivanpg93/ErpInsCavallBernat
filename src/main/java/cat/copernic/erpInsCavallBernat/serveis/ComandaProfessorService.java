package cat.copernic.erpInsCavallBernat.serveis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cat.copernic.erpInsCavallBernat.DAO.ComandaProfessorDAO;
import cat.copernic.erpInsCavallBernat.model.ComandaProfessor;
import cat.copernic.erpInsCavallBernat.model.Usuari;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author adria
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
 */
@Service("comandaProfessorDetailsService")
@Slf4j
public class ComandaProfessorService implements ComandaProfessorServiceInterface {

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
    private ComandaProfessorDAO comandaProfessor;
    @Autowired
    private UsuariServiceInterface usuariService;

    /*LListar productes de la taula producte de la BBDD erp*/
    @Override
    /*La notació @Transactional fa referència a la classe Transactional de Spring Framework.
     *En aquest cas no hi haurà ni COMMITS, ni ROLLBACKS, ja que no modifiquem la informació
     *de la BBDD, per tant, utilitzarem aquesta notació passant-li com a paràmetre readOnly=true
     *perquè només hem de llegir de la BBDD.
     */
    @Transactional(readOnly = true)
    public List<ComandaProfessor> llistarComandesProfessor() {

        /*Cridem al mètode findAll() de CrudRepository perquè ens retorni el llistat de productes de la BBDD.
         *findAll() retorna un objecte, per tant hem de fer un cast perquè l'objecte sigui un List de producte
         */
        return (List<ComandaProfessor>) comandaProfessor.findAll();
    }

    @Override
    public String getRolUserCurrent(User username) {
        return username.getAuthorities().toString().substring(1, username.getAuthorities().toString().length() - 1);
    }

    /*Afegir el producte passat per paràmetre a la taula producte de la BBDD erp*/
    @Override
    @Transactional
    public void crearComandaProfessor(ComandaProfessor comandaProfessor) {

        /*Cridem al mètode save() de CrudRepository perquè afegeixi el producte passat com a paràmetre,
         *a la taula producte de la BBDD erp.
         */
        this.comandaProfessor.save(comandaProfessor);
    }

    /*Eliminar el producte passat per paràmetre de la taula producte de la BBDD erp*/
    @Override
    @Transactional //Igual que en el mètode afegirProducte, modifiquem la informació de la BBDD
    public void eliminarComandaProfessor(ComandaProfessor comandaProfessor) {

        /*Cridem al mètode delete() de CrudRepository perquè elimini el producte passat com a paràmetre,
         *de la taula producte de la BBDD erp.
         */
        this.comandaProfessor.delete(comandaProfessor);

    }

    /*Cercar el producte passat per paràmetre en la taula producte de la BBDD erp*/
    @Override
    @Transactional(readOnly = true) //Igual que en el mètode llistarProductes, no modifiquem la informació de la BBDD
    public ComandaProfessor cercarComandaProfessor(ComandaProfessor comandaProfessor) {

        /*Cridem al mètode findById() de CrudRepository perquè ens retorni el producte passat com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas el producte.
         *
         *Si el producte no existei retornarà null (orElse(null)).
         */
        return this.comandaProfessor.findById(comandaProfessor.getId_comanda()).orElse(null);

    }

    @Override
    public String getCurrentDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date);
        return fecha;

    }
    
    @Override
    public String getActualDatePlusDays(int days) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        date = c.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date);
        return fecha;

    }

    @Override
    public List getMisComandes(User username) {
        var comandesProfessor = llistarComandesProfessor();
        var usuari = username.getUsername();
        var usuaris = usuariService.llistarUsuaris();
        var misComandas = new ArrayList();
        Usuari usr = null;
        for (var usuario : usuaris) {
            if (usuario.getUsername().equals(usuari)) {
                usr = usuario;
            }
        }
        for (var comanda : comandesProfessor) {
            if (comanda.getId_usuari() == usr) {
                misComandas.add(comanda);
            }
        }
        log.info("MIS COMANDAS::: " + misComandas.toString());
        return misComandas;
    }

    @Override
    public List getIds(User username) {
        var usuari = username.getUsername();
        var usuaris = usuariService.llistarUsuaris();
        var rol = getRolUserCurrent(username);
        List<Usuari> ids = new ArrayList<>();
        if (rol.equals("Administrador")) {
            for (var usuario : usuaris) {
                ids.add(usuario);
            }
        } else {
            for (var usuario : usuaris) {
                if (usuario.getUsername().equals(usuari)) {
                    ids.add(usuario);
                }
            }
        }
        return ids;
    }
}
