/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author T440s
 */
public class Server {
    
    public void pokreniServer() throws IOException{
        
        ServerSocket serverSoket = new ServerSocket(9000);
        
        while(true){
            Socket s = serverSoket.accept();
            System.out.println("Klijent se povezao!");
            
            ObradaKlijentskihZahteva klijentNit = new ObradaKlijentskihZahteva(s);
            klijentNit.start();
        }
    }
    
}
