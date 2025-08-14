/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Clan;
import domen.Radnik;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void konekcija() {

        try {
            soket = new Socket("localhost", 9000);
            System.out.println("Klijent se povezao");
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Nije moguce uspostaviti vezu sa serverom!");
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Radnik prijava(Radnik r) {
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, r);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        Radnik ulogovani = (Radnik) odgovor.getOdgovor();

        return ulogovani;
    }

    public List<Clan> ucitajListuClanova() {

        List<Clan> listaClanova = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_LISTU_CLANOVA, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        listaClanova = (List<Clan>) odgovor.getOdgovor();

        return listaClanova;

    }

}
