package src;

import java.util.ArrayList;
import java.util.concurrent.*;

import java.io.*;
import java.lang.Thread;
import java.util.*;
import src.APp;

public class Escalonador implements Runnable {

    private ArrayList listaDeProntos;
    private Semaphore Jorginho;
    private Semaphore Cleusa;
    private GerenteDeMemoria GDM;

    public Escalonador(ArrayList Arthur, Semaphore Irineu, Semaphore Tadeu) {

        Jorginho = Irineu;
        Cleusa = Tadeu;
        listaDeProntos = Arthur;
        GDM = new GerenteDeMemoria();
    }

    public void run() {

        while (true) {
            if (APp.Flag == false) {
                try {
                    Jorginho.acquire();
                    Cleusa.acquire();
                } catch (InterruptedException Err) {
                    System.out.println(Err);
                }

                if (listaDeProntos.size() >= 1 && GDM.consulta() != true && APp.FlagCPU != true ) {

                    GDM.allocate((pCB) listaDeProntos.remove(0));
                    
                }
                APp.Flag = true;
                Jorginho.release();
                Cleusa.release();
            }
            
        }

    }
}