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
public class StavkaPotvrdeOIznajmljivanju implements ApstraktniDomenskiObjekat{
    
    private Long redniBroj;
    private String napomena;
    private DrustvenaIgra drustvenaIgra;
    private Long potvrdaID;

    public StavkaPotvrdeOIznajmljivanju() {
    }

    public StavkaPotvrdeOIznajmljivanju(Long redniBroj, String napomena, DrustvenaIgra drustvenaIgra) {
        this.redniBroj = redniBroj;
        this.napomena = napomena;
        this.drustvenaIgra = drustvenaIgra;
    }

    public DrustvenaIgra getDrustvenaIgra() {
        return drustvenaIgra;
    }

    public void setDrustvenaIgra(DrustvenaIgra drustvenaIgra) {
        this.drustvenaIgra = drustvenaIgra;
    }

    public Long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Long redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public void setPotvrdaID(Long potvrdaID) {
        this.potvrdaID = potvrdaID;
    }

    public Long getPotvrdaID() {
        return potvrdaID;
    }

    
    @Override
    public String toString() {
        return "StavkaPotvrdeOIznajmljivanju{" + "redniBroj=" + redniBroj + ", drustvenaIgra=" + drustvenaIgra + '}';
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
        final StavkaPotvrdeOIznajmljivanju other = (StavkaPotvrdeOIznajmljivanju) obj;
        if (!Objects.equals(this.redniBroj, other.redniBroj)) {
            return false;
        }
        return Objects.equals(this.drustvenaIgra, other.drustvenaIgra);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkapotvrdeoiznajmljivanju";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> listaStavki = new ArrayList<>();
        
        while(rs.next()){
            StavkaPotvrdeOIznajmljivanju stavka = new StavkaPotvrdeOIznajmljivanju();
            stavka.setRedniBroj(rs.getLong("redniBroj"));
            stavka.setNapomena(rs.getString("napomena"));
            
            DrustvenaIgra drig = new DrustvenaIgra();
            drig.setIgraID(rs.getLong("drustvenaigra.igraID"));
            drig.setNaziv(rs.getString("naziv"));
            drig.setTip(rs.getString("tip"));
            drig.setOpis(rs.getString("opis"));
            stavka.setDrustvenaIgra(drig);
            
            listaStavki.add(stavka);
        }
        
        return listaStavki;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "redniBroj, potvrdaOIznajmljivanju, napomena, drustvenaIgra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return redniBroj + ", " + potvrdaID + ", '" + napomena + "', " + drustvenaIgra.getIgraID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
