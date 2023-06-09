package cat.copernic.erpInsCavallBernat.serveis;

import cat.copernic.erpInsCavallBernat.DAO.UsuariDAO;
import cat.copernic.erpInsCavallBernat.eines.EncriptadorContrasenya;
import cat.copernic.erpInsCavallBernat.model.Rol;
import cat.copernic.erpInsCavallBernat.model.Usuari;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivan
 */
@Service("userDetailsService")
@Slf4j
public class UsuariService implements UserDetailsService, UsuariServiceInterface {

    /*Atribut que defineix un UsuariDAO, injectant els seu mètodes a aquesta classe (@Autowired),
     *els quals ens permeten interactuar amb les taules de la BBDD pels usuaris i rols
     */
    @Autowired
    private UsuariDAO usuariDAO;

    /*Únic mètode de la interface UserDetailsService que retornarà un usuari a partir del nom d'usuari.
     *L'usuari que retorna es de tipus UserDetails que és una interface de Spring Security que defineix
     *els mètodes necessaris per treballar amb un usuari.
     */
    @Override
    @Transactional(readOnly = true) //Consulta només de lectura
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*Implementem el mètode definits en UsuariDao. Hem de pensar que aquest és un mètode predefinit
         *de Spring Security i, per tant, no hem de desnvolupar cap codi, ja ve donat per Spring Security.
         *Aquest mètode ens retornarà l'usuari a partir de nom d'usuari passat per paràmetre.
         */
        Usuari usuari = usuariDAO.findByUsername(username);

        //Comprovem que existeix l'usuari
        if (usuari == null) { //Si no existeix l'usuari...

            //Llancem una excepció de tipus UsernameNotFoundException             
            throw new UsernameNotFoundException(username);

        }

        /*Si l'usuari existeix, recuperarem els rols associats a aquest usuari.
         *Els rols els guardarem dins un ArrayList de tipus genèric GrantedAuthority,
         *tipus genèric de Spring Security necessari perque Spring Security pugui
         *treballar amb els rols.
         *GrantedAuthority, és una interface.
         */
        var rols = new ArrayList<GrantedAuthority>();

        /*Fem un recorregut pels rols de l'usuari actual guardats en l'atribut rols de la classe Usuari
         *per guardar-los com objectes de tipus GrantedAuthority en la variable rols.
         *Per convertir un rol en un objecte de tipus GrantedAuthority, instanciem un objecte de la 
         *classe SimpleGrantedAuthority la qual implementa GrantedAuthority, passant-li com a paràmetre 
         *el nom del rol.
         */
        for (Rol rol : usuari.getRols()) {
            rols.add(new SimpleGrantedAuthority(rol.getNom()));
        }

        /*Retornme el nou usuari de tipus UserDetails mitjançant la classe User d'Spring Security,
         *la qual implementa la interface UserDetails.
         *Com a paràmetres passem el nom d'usuari, la contrasenya i els rols del l'usuari alqual
         *li correspon el nom d'usuari passat com a paràmetre.
         */
        return new User(usuari.getUsername(), usuari.getPassword(), rols);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuari> llistarUsuaris() {
        return (List<Usuari>) usuariDAO.findAll();
    }

    @Override
    public String getRolUserCurrent(User username) {
        return username.getAuthorities().toString().substring(1, username.getAuthorities().toString().length() - 1);
    }

    @Override
    public void crearUsuari(Usuari usuari) {
        usuari.setPassword(EncriptadorContrasenya.encriptarContrasenya(usuari.getPassword()));
        this.usuariDAO.save(usuari);
    }

    @Override
    public void eliminarUsuari(Usuari usuari) {
        this.usuariDAO.delete(usuari);
    }

    @Override
    public Usuari cercarUsuari(Usuari usuari) {
        return this.usuariDAO.findById(usuari.getId_usuari()).orElse(null);
    }

}
