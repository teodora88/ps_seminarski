package niti;

import domen.Radnik;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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
