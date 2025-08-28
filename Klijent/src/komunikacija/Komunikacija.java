/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Clan;
import domen.DrustvenaIgra;
import domen.Grad;
import domen.PotvrdaOIznajmljivanju;
import domen.Radnik;
import domen.StavkaPotvrdeOIznajmljivanju;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author T440s
 */
public class Komunikacija {

    private static Komunikacija instanca;
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {
    }

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public void konekcija() throws IOException {

        soket = new Socket("localhost", 9000);
        System.out.println("Klijent se povezao");
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);

    }

    public Radnik prijava(Radnik r) {
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, r);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        Radnik ulogovani = (Radnik) odgovor.getOdgovor();

        return ulogovani;
    }

    public List<Clan> ucitajListuClanova() {

        List<Clan> listaClanova;
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_LISTU_CLANOVA, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        listaClanova = (List<Clan>) odgovor.getOdgovor();

        return listaClanova;

    }

    public void obrisiClana(Clan c) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.OBRISI_CLANA, c);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

    }

    public List<Grad> vratiListuGradova() {

        List<Grad> listaClanova;

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_GRADOVA, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        listaClanova = (List<Grad>) odgovor.getOdgovor();

        return listaClanova;
    }

    public void dodajClana(Clan c) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.DODAJ_CLANA, c);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

    }

    public void izmeniClana(Clan c) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.IZMENI_CLANA, c);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

    }

    public List<DrustvenaIgra> ucitajListuIgara() {

        List<DrustvenaIgra> listaIgara;
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_LISTU_IGARA, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        listaIgara = (List<DrustvenaIgra>) odgovor.getOdgovor();

        return listaIgara;

    }

    public List<PotvrdaOIznajmljivanju> ucitajListuPotvrda() {

        List<PotvrdaOIznajmljivanju> listaPotvrda;
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_LISTU_POTVRDA, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        listaPotvrda = (List<PotvrdaOIznajmljivanju>) odgovor.getOdgovor();

        return listaPotvrda;

    }

    public List<StavkaPotvrdeOIznajmljivanju> usitajListuStavki(Long potvrdaID) {

        List<StavkaPotvrdeOIznajmljivanju> listaStavki;
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_LISTU_STAVKI, potvrdaID);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        listaStavki = (List<StavkaPotvrdeOIznajmljivanju>) odgovor.getOdgovor();

        return listaStavki;

    }

    public void dodajPotvrdu(PotvrdaOIznajmljivanju pot) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.DODAJ_POTVRDU, pot);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

    }

    public void izmeniPotvrdu(PotvrdaOIznajmljivanju pot) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.IZMENI_POTVRDU, pot);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

    }

    public List<Clan> nadjiClanove(Clan c) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.NADJI_CLANOVE, c);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Clan>) odgovor.getOdgovor();

    }

    public List<DrustvenaIgra> nadjiDrustveneIgre(DrustvenaIgra di) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.NADJI_IGRE, di);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<DrustvenaIgra>) odgovor.getOdgovor();
    }

    public List<PotvrdaOIznajmljivanju> nadjiPotvrde(Clan c) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.NADJI_POTVRDE, c);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<PotvrdaOIznajmljivanju>) odgovor.getOdgovor();
    }

}
