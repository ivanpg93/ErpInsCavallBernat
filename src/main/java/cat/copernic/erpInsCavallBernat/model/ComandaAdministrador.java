package cat.copernic.erpInsCavallBernat.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "comanda_centralitzada")
public class ComandaAdministrador implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @Id //Indica al sistema que l'atribut id_Producte és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generarem l'id
    private long id_comanda_centralitzada;

    /*Validació per comprovar que el nom no està buit. Com a paràmetre no li passem res, per tant
     *ens mostrarà el missatge per defecte del sitema.
     */
    @NotEmpty
    private Date  data_Arribada;
  
}