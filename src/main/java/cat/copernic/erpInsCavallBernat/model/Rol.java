package cat.copernic.erpInsCavallBernat.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author ivan
 */
@Data
@Entity
@Table(name="rol")
public class Rol implements Serializable{
    
    private static final long serialVersionUID=1L;

    @Id //L'atribut idRol és la clau primària de la BBDD
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Generació autonumèrica de l'id
    private long id_rol;
    
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String nom;
    
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_usuari", referencedColumnName = "id_usuari")
//    private Usuari id_usuari;
 
    
}
