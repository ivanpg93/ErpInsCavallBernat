/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.erpInsCavallBernat.eines;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ivan
 */
public class EncriptadorContrasenya {

    public static void main(String[] args) {

        var password = "123";
        System.out.println("Contrasenya:" + password);
        System.out.println("Contrasenya encriptada:" + encriptarContrasenya(password));
    }

    public static String encriptarContrasenya(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
