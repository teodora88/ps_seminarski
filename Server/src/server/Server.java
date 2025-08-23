/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author T440s
 */
public class Server extends Thread {

    boolean kraj = false;
    ServerSocket serverSoket;
    List<ObradaKlijentskihZahteva> listaKlijenata;

    public Server() {
        listaKlijenata = new ArrayList<>();
    }

    @Override
    public void run() {

        try {
            serverSoket = new ServerSocket(9000);

            while (!kraj) {

                try {
                    System.out.println("Cekanje klijenta");
                    Socket s = serverSoket.accept();
                    System.out.println("Klijent se povezao");

                    ObradaKlijentskihZahteva klijentNit = new ObradaKlijentskihZahteva(s);
                    listaKlijenata.add(klijentNit);
                    klijentNit.start();

                } catch (SocketException se) {
                    
                    if (kraj) {
                        System.out.println("Server je zaustavljen.");
                    } else {
                        se.printStackTrace();
                    }

                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : listaKlijenata) {
                k.prekiniNit();
            }
            serverSoket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
