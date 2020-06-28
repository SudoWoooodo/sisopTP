package src;

import java.util.*;
import java.util.concurrent.Semaphore;
import src.APp;


public class shell implements Runnable {

    private gerenteDeProcessos GDP;
    private Semaphore Klebinho;


    public shell(ArrayList Maicon, Semaphore Irineu) throws InterruptedException {

        GDP = new gerenteDeProcessos(Maicon);
        Klebinho = Irineu;
        
    }

    public void run() {

        Scanner le = new Scanner(System.in);
        int i = 0;

        while (i == 0) {
            if (APp.Flag == true) {
                try {
                    Klebinho.acquire();
                } catch (InterruptedException Err) {
                }

                // verificaçao
                System.out.println("Digite os comandos:");
                String Resposta = le.nextLine();
                if (Resposta.equals("quit")) {
                    i++;
                } else {
                    GDP.loadArquivo(Resposta);
                    APp.Flag = false;
                }
                Klebinho.release();
            }

        }
    }

}
