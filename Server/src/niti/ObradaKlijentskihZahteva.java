package niti;

import domen.Clan;
import domen.DrustvenaIgra;
import domen.Grad;
import domen.PotvrdaOIznajmljivanju;
import domen.Radnik;
import domen.StavkaPotvrdeOIznajmljivanju;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;


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
                    case UCITAJ_LISTU_IGARA:
                        List<DrustvenaIgra> listaIgara = Kontroler.getInstanca().ucitajListuIgara();
                        odgovor.setOdgovor(listaIgara);
                        break;
                    case UCITAJ_LISTU_POTVRDA:
                        List<PotvrdaOIznajmljivanju> listaPotvrda = Kontroler.getInstanca().ucitajListuPotvrda();
                        odgovor.setOdgovor(listaPotvrda);
                        break;
                    case UCITAJ_LISTU_STAVKI:
                        List<StavkaPotvrdeOIznajmljivanju> listaStavki = Kontroler.getInstanca().ucitajListuStavki((Long) zahtev.getParametar());
                        odgovor.setOdgovor(listaStavki);
                        break;
                    case DODAJ_POTVRDU:
                        try {
                        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) zahtev.getParametar();
                        Kontroler.getInstanca().dodajPotvrdu(pot);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case IZMENI_POTVRDU:
                        try {
                        PotvrdaOIznajmljivanju pot = (PotvrdaOIznajmljivanju) zahtev.getParametar();
                        Kontroler.getInstanca().izmeniPotvrdu(pot);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    default:
                        System.out.println("Greška,ta operacija ne posoji!");

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
