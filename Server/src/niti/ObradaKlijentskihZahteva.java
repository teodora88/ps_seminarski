package niti;

import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author T440s
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    Socket soket;
    Posiljalac posiljalac;
    Primalac primalac;

    public ObradaKlijentskihZahteva(Socket soket) {
        this.soket = soket;
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
    }
    
    

    @Override
    public void run() {
        
        while(true){
            
            Zahtev zahtev = (Zahtev) primalac.primi();
            Odgovor odgovor = new Odgovor();
            
            switch(zahtev.getOperacija()){
                
            }
            
            posiljalac.posalji(odgovor);
        }
        
    }
    
    
    
}
