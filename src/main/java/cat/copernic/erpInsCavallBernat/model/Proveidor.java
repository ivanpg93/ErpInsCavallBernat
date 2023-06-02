/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.model;

import java.io.Serializable;
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
 * @author rpuig
 */
@Data
@Entity
@Table(name="proveidor")
public class Proveidor implements Serializable {
    private static final long serialVersionUID=1L;
    @Id   
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String cif;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String nom;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String email;
    @NotNull
    private int telefon;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String adreca;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String contacte;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String descripcio;
    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String observacio;

}




