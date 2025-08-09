/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skladiste.baza.implementacija;

import domen.ApstraktniDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import skladiste.baza.SkladisteBazePodataka;
import java.sql.*;
import skladiste.baza.Konekcija;

/**
 *
 * @author T440s
 */
public class GenerickoSkladisteBaze implements SkladisteBazePodataka<ApstraktniDomenskiObjekat> {

    // na vezbama se zove DbRepositoryGeneric
    @Override
    public List<ApstraktniDomenskiObjekat> vratiSve() {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        return lista;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        String upit = "SELECT FROM " + param.vratiNazivTabele();

        if (uslov != null) {
            upit += uslov;
        }

        System.out.println(upit);

        Statement statement = Konekcija.getInstanca().getKonekcija().createStatement();
        ResultSet rs = statement.executeQuery(upit);

        lista = param.vratiListu(rs);

        rs.close();
        statement.close();
        return lista;
    }

    @Override
    public void dodaj(ApstraktniDomenskiObjekat param) throws Exception {

        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje()
                + " ) VALUES (" + param.vratiVrednostiZaUbacivanje() + " )";
        System.out.println(upit);

        Statement statement = Konekcija.getInstanca().getKonekcija().createStatement();
        statement.executeUpdate(upit);

        statement.close();

    }

    @Override
    public void izmeni(ApstraktniDomenskiObjekat param) throws Exception {

        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostZaIzmenu();
        System.out.println(upit);

        Statement statement = Konekcija.getInstanca().getKonekcija().createStatement();
        statement.executeUpdate(upit);

        statement.close();
    }

    @Override
    public void obrisi(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);

        Statement statement = Konekcija.getInstanca().getKonekcija().createStatement();
        statement.executeUpdate(upit);

        statement.close();
    }

}
