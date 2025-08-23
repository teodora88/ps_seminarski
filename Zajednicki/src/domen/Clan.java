/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author T440s
 */
public class Clan implements ApstraktniDomenskiObjekat{
    
    private Long clanID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private Date datumUclanjenja;
    private Grad grad;

    public Clan() {
    }

    public Clan(Long clanID, String ime, String prezime, Date datumRodjenja, Date datumUclanjenja, Grad grad) {
        this.clanID = clanID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.datumUclanjenja = datumUclanjenja;
        this.grad = grad;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public Long getClanID() {
        return clanID;
    }

    public void setClanID(Long clanID) {
        this.clanID = clanID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.clanID);
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
        final Clan other = (Clan) obj;
        return Objects.equals(this.clanID, other.clanID);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "clan";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> listaClanova = new ArrayList<>();
        while(rs.next()){
            Long cID = rs.getLong("clan.clanID");
            String imeC = rs.getString("ime");
            String prezimeC = rs.getString("prezime");
            Date datRodj = rs.getDate("datumRodjenja");
            Date datUcl = rs.getDate("datumUclanjenja");
            Grad g = new Grad();
            g.setPostanskiBroj(rs.getLong("grad.postanskiBroj"));
            g.setNaziv(rs.getString("grad.naziv"));
            Clan c = new Clan(cID, imeC, prezimeC, datRodj, datUcl, g);
            listaClanova.add(c);
        }
        return listaClanova;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, datumRodjenja, datumUclanjenja, grad";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + datumRodjenja + "', '" + datumUclanjenja + 
                "', " + grad.getPostanskiBroj();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "clan.clanID = " + clanID;
    }

    
    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', datumRodjenja = '" + datumRodjenja +
                "', datumUclanjenja = '" + datumUclanjenja + "', grad = " + grad.getPostanskiBroj();
    }
    
    
    
}
