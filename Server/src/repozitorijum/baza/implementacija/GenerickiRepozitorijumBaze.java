/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.baza.implementacija;

import domen.ApstraktniDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import repozitorijum.baza.Konekcija;
import repozitorijum.baza.RepozitorijumBaze;

/**
 *
 * @author T440s
 */
public class GenerickiRepozitorijumBaze implements RepozitorijumBaze<ApstraktniDomenskiObjekat> {

    
    @Override
    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        String upit = "SELECT * FROM " + param.vratiNazivTabele();

        if (uslov != null) {
            upit += uslov;
        }

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
        
        Statement statement = Konekcija.getInstanca().getKonekcija().createStatement();
        statement.executeUpdate(upit);

        statement.close();

    }

    @Override
    public void izmeni(ApstraktniDomenskiObjekat param) throws Exception {

        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostZaIzmenu()
                + " WHERE " + param.vratiPrimarniKljuc();
        
        Statement statement = Konekcija.getInstanca().getKonekcija().createStatement();
        statement.executeUpdate(upit);

        statement.close();
        
    }

    @Override
    public void obrisi(ApstraktniDomenskiObjekat param) throws Exception {
        
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();

        Statement statement = Konekcija.getInstanca().getKonekcija().createStatement();
        statement.executeUpdate(upit);

        statement.close();
        
    }

    @Override
    public int dodajIVratiPK(ApstraktniDomenskiObjekat param) throws Exception {

        String upit = "INSERT INTO " + param.vratiNazivTabele()
                + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES ("
                + param.vratiVrednostiZaUbacivanje() + ")";
                
        PreparedStatement prStat = Konekcija.getInstanca().getKonekcija().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        prStat.executeUpdate();
        
        ResultSet rs = prStat.getGeneratedKeys();
        int genKljuc = -1;
        if(rs.next()){
            genKljuc = rs.getInt(1);
        }
        
        rs.close();
        prStat.close();
        
        return genKljuc;

    }

}
