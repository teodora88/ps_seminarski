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
public class PotvrdaOIznajmljivanju implements ApstraktniDomenskiObjekat {

    private Long potvrdaID;
    private Date datumIznajmljivanja;
    private Date datumVracanja;
    private Clan clan;
    private Radnik radnik;
    private List<StavkaPotvrdeOIznajmljivanju> listaStavki = new ArrayList<>();

    public PotvrdaOIznajmljivanju() {
    }

    public PotvrdaOIznajmljivanju(Long potvrdaID, Date datumIznajmljivanja, Clan clan, Radnik radnik, List<StavkaPotvrdeOIznajmljivanju> listaStavki) {
        this.potvrdaID = potvrdaID;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.clan = clan;
        this.radnik = radnik;
        this.listaStavki = listaStavki;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Long getPotvrdaID() {
        return potvrdaID;
    }

    public void setPotvrdaID(Long potvrdaID) {
        this.potvrdaID = potvrdaID;
    }

    public Date getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(Date datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public List<StavkaPotvrdeOIznajmljivanju> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaPotvrdeOIznajmljivanju> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public String toString() {
        return "PotvrdaOIznajmljivanju{" + "potvrdaID=" + potvrdaID + ", datumIznajmljivanja=" + datumIznajmljivanja + ", datumVracanja=" + datumVracanja + ", clan=" + clan + ", radnik=" + radnik + ", listaStavki=" + listaStavki + '}';
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
        final PotvrdaOIznajmljivanju other = (PotvrdaOIznajmljivanju) obj;
        return Objects.equals(this.potvrdaID, other.potvrdaID);
    }

    @Override
    public String vratiNazivTabele() {
        return "potvrdaoiznajmljivanju";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            PotvrdaOIznajmljivanju potvrda = new PotvrdaOIznajmljivanju();
            potvrda.setPotvrdaID(rs.getLong("potvrdaoiznajmljivanju.potvrdaID"));
            potvrda.setDatumIznajmljivanja(rs.getDate("datumIznajmljivanja"));
            potvrda.setDatumVracanja(rs.getDate("datumVracanja"));

            Clan c = new Clan();
            c.setClanID(rs.getLong("clan.clanID"));
            c.setIme(rs.getString("clan.ime"));
            c.setPrezime(rs.getString("clan.prezime"));
            potvrda.setClan(c);
            
            Radnik r = new Radnik();
            r.setRadnikID(rs.getLong("radnik.radnikID"));
            r.setIme(rs.getString("radnik.ime"));
            r.setPrezime(rs.getString("radnik.prezime"));
            potvrda.setRadnik(r);
            
            potvrda.setListaStavki(new ArrayList<>());

            lista.add(potvrda);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIznajmljivanja, datumVracanja, clan, radnik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        String datumVracanjaStr = "NULL";
        return "'" + datumIznajmljivanja + "', " + datumVracanjaStr + ", " + clan.getClanID() + ", " + radnik.getRadnikID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "potvrdaoiznajmljivanju.potvrdaID = " + potvrdaID;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumVracanja = '" + datumVracanja + "'";
    }

}
