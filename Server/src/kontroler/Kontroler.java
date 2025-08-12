/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Radnik;
import operacija.prijava.PrijavaSO;

/**
 *
 * @author T440s
 */
public class Kontroler {
    
    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler getInstanca() {
        if(instanca == null){
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Radnik prijava(Radnik r) throws Exception {
        
        PrijavaSO operacija = new PrijavaSO();
        operacija.izvrsi(r, null);
        System.out.println("klasa kontroler" + operacija.getRadnik());
        return operacija.getRadnik();
        
    }
    
}
