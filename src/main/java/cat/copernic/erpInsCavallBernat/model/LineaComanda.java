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
 * @author adria
 */
@Data
@Entity
@Table(name = "linea_comanda")
public class LineaComanda implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut id_Producte és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generarem l'id
    private long id_linea_comanda;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comanda", referencedColumnName = "id_comanda")
    private ComandaProfessor id_comanda;
  
    /*Validació per comprovar que el nom no està buit. Com a paràmetre li passem el missatge
     *que volem que aparegui.
     */
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producte", referencedColumnName = "id_producte")
    private Producte  id_Producte;
    
    @NotNull
    private long  quantitat;
    
    private String observacio;
    
    private String  pre_elavoracions;
  
}

