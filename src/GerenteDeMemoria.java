package src;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.io.*;
import java.lang.Thread;

public class GerenteDeMemoria {

    public static volatile posicaoDeMemoria[] memoria;
    public static volatile Integer[] regis = new Integer[8];
    public static volatile Particoes Kleber;
    public static volatile boolean cheio;
    public static volatile int PC;
    public static volatile pCB Edmundo;
    

    public GerenteDeMemoria() {

        memoria = new posicaoDeMemoria[1024];
        for (int k = 0; k < memoria.length; k++) {
            memoria[k] = new posicaoDeMemoria(null, null, null, -99);
        }
        PC = 0;
        for (int i = 0; i < regis.length; i++) {

            regis[i] = 0;
        }

        Kleber = new Particoes();
        cheio = false;

    }

    public void allocate(pCB Ricardinho) {

        int k = 0;
        while (k < 4) {
            if (Kleber.getOcup(k) != true) {

                int comeco = Kleber.traduz(k);
                Kleber.setOcup(k);
                Kleber.setProgram(Ricardinho, k);

                for (int i = 0; i < Ricardinho.getRomario().size(); i++) {

                    memoria[comeco] = (posicaoDeMemoria) Ricardinho.getRomario().get(i);
                    comeco++;
                }
                break;
            }
            k++;

        }

        if (k == 3) {
            cheio = true;
        }

    }

    public boolean consulta() {
        return cheio;
    }
}
