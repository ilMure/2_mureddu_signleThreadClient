package it.fi.meucci;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaRisposta = null;
    BufferedReader inDalClient = null;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try {
            System.out.println("SERVER is running");
            if (server == null) server = new ServerSocket(6789);
            client = server.accept();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore durante istanza del server");
        }
        return client;
    }

    public void comunica(){
        try {
            System.out.println("ciao client, scrivi una frase che trasfermerò in maiuscolo. Sono in attesa...");
            for (;;){
                stringaRicevuta = inDalClient.readLine();
                
                System.out.println("ricevuta la stringa del cliente: " + stringaRicevuta); 
                //controllo contenuto stringa
                if (stringaRicevuta == null || stringaRicevuta.equals("BYE")){
                    System.out.println("SERVER: ho terminato... " +stringaRicevuta);
                    break;
                }else{
                    System.out.println("invio la stringa di risposta al client...");
                    stringaRisposta = stringaRicevuta.toUpperCase();
                    outVersoClient.writeBytes(stringaRisposta+"\n");
                }                  
            }
            client.close();
            
        } catch (Exception e) {
            System.out.println("Errore durante la comunicazione");
        }
        
    }

    
}