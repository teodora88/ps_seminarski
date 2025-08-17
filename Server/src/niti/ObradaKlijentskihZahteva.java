package niti;

import domen.Clan;
import domen.Grad;
import domen.Radnik;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Odgovor;
import static komunikacija.Operacija.LOGIN;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author T440s
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket soket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket soket) {
        this.soket = soket;
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
    }

    @Override
    public void run() {

        while (!kraj) {

            try {

                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Radnik r = (Radnik) zahtev.getParametar();
                        Radnik ulogovani = Kontroler.getInstanca().prijava(r);
                        odgovor.setOdgovor(ulogovani);
                        break;
                    case UCITAJ_LISTU_CLANOVA:
                        List<Clan> listaClanova = Kontroler.getInstanca().ucitajListuClanova();
                        odgovor.setOdgovor(listaClanova);
                        break;
                    case OBRISI_CLANA:
                        try {
                        Clan c = (Clan) zahtev.getParametar();
                        Kontroler.getInstanca().obrisiClana(c);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case VRATI_LISTU_GRADOVA:
                        List<Grad> listaGradova = Kontroler.getInstanca().ucitajListuGradova();
                        odgovor.setOdgovor(listaGradova);
                        break;
                    case DODAJ_CLANA:
                        try {
                        Clan c = (Clan) zahtev.getParametar();
                        Kontroler.getInstanca().dodajClana(c);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case IZMENI_CLANA:
                        try {
                        Clan c = (Clan) zahtev.getParametar();
                        Kontroler.getInstanca().izmeniClana(c);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    default:
                        System.out.println("Greska,ta operacija ne posoji!");

                }

                posiljalac.posalji(odgovor);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void prekiniNit() {
        kraj = true;
        try {
            if (!soket.isClosed()) {
                soket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.interrupt();
    }

}
