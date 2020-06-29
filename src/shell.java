package src;

import java.util.*;
import java.util.concurrent.Semaphore;
import src.App;

public class shell implements Runnable {

    private gerenteDeProcessos GDP;
    private Semaphore Semaforo3;

    public shell(ArrayList array, Semaphore semaforo) throws InterruptedException {

        GDP = new gerenteDeProcessos(array);
        Semaforo3 = semaforo;

    }

    public void run() {

        Scanner le = new Scanner(System.in);
        int i = 0;

        while (i == 0) {
            if (App.Flag == true) {
                try {
                    Semaforo3.acquire();
                } catch (InterruptedException Err) {
                }

                System.out.println("Digite os comandos: ");
                String Resposta = le.nextLine();
                if (Resposta.equals("quit")) {
                    i++;
                } else {
                    GDP.loadArquivo(Resposta);
                    App.Flag = false;
                    App.FlagCPU = false;
                }
                Semaforo3.release();
            }

        }
    }

}
