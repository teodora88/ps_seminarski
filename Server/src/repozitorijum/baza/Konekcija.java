/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.baza;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 *
 * @author T440s
 */
public class Konekcija {
    
    private static Konekcija instanca;
    private Connection konekcija;

    private Konekcija() {

        try {

            if (konekcija == null || konekcija.isClosed()) {
                String url = Konfiguracija.getInstanca().getProperty("url");
                String username = Konfiguracija.getInstanca().getProperty("username");
                String password = Konfiguracija.getInstanca().getProperty("password");

                konekcija = DriverManager.getConnection(url, username, password);
                konekcija.setAutoCommit(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Konekcija getInstanca() {
        if (instanca == null) {
            instanca = new Konekcija();
        }
        return instanca;
    }

    public Connection getKonekcija() {
        return konekcija;
    }

}
