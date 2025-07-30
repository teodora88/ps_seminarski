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
public class Grad implements ApstraktniDomenskiObjekat{
    
    private Long postanskiBroj;
    private String naziv;

    public Grad() {
    }

    public Grad(Long postanskiBroj, String naziv) {
        this.postanskiBroj = postanskiBroj;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Long postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Grad other = (Grad) obj;
        return Objects.equals(this.postanskiBroj, other.postanskiBroj);
    }

    @Override
    public String vratiNazivTabele() {
        return "grad";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
            Long postanskiBroj = rs.getLong("grad.postanskiBroj");
            String naziv = rs.getString("grad.naziv");
            
            Grad g = new Grad(postanskiBroj, naziv);
            lista.add(g);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "postanskiBroj, naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return postanskiBroj + ", '" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "grad.postanskiBroj = " + postanskiBroj;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "postanskiBroj = " + postanskiBroj + ", naziv = '" + naziv + "'"; 
    }
    
    
    
}
