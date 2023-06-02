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
import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 *
 * @author ivan
 */
@Data
@Entity
@Table(name = "producte")
public class Producte implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut id_Producte és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generarem l'id
    private long id_Producte;
    /*Validació per comprovar que el nom no està buit. Com a paràmetre li passem el missatge
     *que volem que aparegui.
     */
    @NotEmpty //Validem un nombre mínim de caràcters
    private String nom;
    /*Validació per comprovar que el nom no està buit. Com a paràmetre no li passem res, per tant
     *ens mostrarà el missatge per defecte del sitema.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_unitat", referencedColumnName = "id_unitat")
    private Unitat unitat;
    
    @NotNull
    private double preu;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;
    
    @NotNull
    private int stock;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cif_proveidor", referencedColumnName = "cif")
    private Proveidor proveidor;
  
}
