package src;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.io.*;
import java.lang.Thread;

public class APp {

    static volatile ArrayList Josue = new ArrayList<>();
    static volatile boolean Flag = true;
    static volatile boolean FlagCPU = false;

    public static void main(String[] args) throws InterruptedException {

        Semaphore Batata = new Semaphore(1);
        Semaphore Batatinha = new Semaphore(1);

        shell shell = new shell(Josue, Batata);
        Thread Terminal = new Thread(shell);

        Escalonador escalonador = new Escalonador(Josue, Batata, Batatinha);
        Thread Escalonator = new Thread(escalonador);

        CPU CPU = new CPU(Batatinha);
        Thread Cpu = new Thread(CPU);

        Terminal.start();

        Escalonator.start();

        Cpu.start();

    }
}