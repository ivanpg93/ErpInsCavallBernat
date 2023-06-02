package cat.copernic.erpInsCavallBernat.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ivan
 */
@Data
@Entity
@Table(name = "comanda")
public class ComandaProfessor implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut id_Producte és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generarem l'id
    private long id_comanda;
    /*Validació per comprovar que el nom no està buit. Com a paràmetre li passem el missatge
     *que volem que aparegui.
     */
    @NotEmpty //Validem un nombre mínim de caràcters
    private String nom;
    /*Validació per comprovar que el nom no està buit. Com a paràmetre no li passem res, per tant
     *ens mostrarà el missatge per defecte del sitema.
     */
    
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String data;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String data_Arribada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuari", referencedColumnName = "id_usuari")
    private Usuari id_usuari;

    private long id_centralitzada = 0;

    private boolean valida = false;

    private long id_antiga = id_comanda;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_modul", referencedColumnName = "id_modul")
    private Modul modul;
}
