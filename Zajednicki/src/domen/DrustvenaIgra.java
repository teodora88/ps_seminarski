/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author T440s
 */
public class DrustvenaIgra implements ApstraktniDomenskiObjekat{
    
    private Long igraID;
    private String naziv;
    private String tip;
    private String opis;

    public DrustvenaIgra() {
    }

    public DrustvenaIgra(Long igraID, String naziv, String tip, String opis) {
        this.igraID = igraID;
        this.naziv = naziv;
        this.tip = tip;
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getIgraID() {
        return igraID;
    }

    public void setIgraID(Long igraID) {
        this.igraID = igraID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return naziv + tip;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrustvenaIgra other = (DrustvenaIgra) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "drustvenaigra";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
            Long igraID = rs.getLong("drustvenaigra.igraID");
            String naziv = rs.getString("drustvenaigra.naziv");
            String tip = rs.getString("drustvenaigra.tip");
            String opis = rs.getString("drustvenaigra.opis");
            
            DrustvenaIgra igra = new DrustvenaIgra(igraID, naziv, tip, opis);
            lista.add(igra);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, tip, opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + tip + "', '" + opis + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "drustvenaigra.igraID = " + igraID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv = '" + naziv + "', tip = '" + tip + "', opis = '" + opis + "'";
    }
    
    
    
}
