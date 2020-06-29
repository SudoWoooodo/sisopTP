package src;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.io.*;
import java.lang.Thread;

public class GerenteDeMemoria {

    public static volatile posicaoDeMemoria[] memoria;
    public static volatile Integer[] regis = new Integer[8];
    public static volatile Particoes Particao;
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

        Particao = new Particoes();
        cheio = false;

    }

    public void allocate(pCB pcb) {

        int k = 0;
        while (k < 4) {
            if (Particao.getOcup(k) != true) {
                System.out.println("Salvando o exercício " + pcb.getId() + " na partição " + k);
                int comeco = Particao.traduz(k);
                Particao.setOcup(k);
                Particao.setProgram(pcb, k);
                pcb.setSafe(comeco);

                for (int i = 0; i < pcb.getArray().size(); i++) {

                    memoria[comeco] = (posicaoDeMemoria) pcb.getArray().get(i);
                    comeco++;
                }
                break;
            }
            k++;

        }

        if (k == 3) {
            System.out.println("Memória lotada!");
            cheio = true;
        }

    }

    public boolean consulta() {
        return cheio;
    }
}
