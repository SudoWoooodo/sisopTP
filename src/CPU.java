package src;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import src.GerenteDeMemoria;

public class CPU implements Runnable {

    ArrayList<posicaoDeMemoria> programa;
    private int regisAux;
    private int regisAux1;
    private String func;
    private int count;
    private Semaphore Semaforo6;
    public static volatile int i = 0;

    public CPU(Semaphore semaforo) {
        Semaforo6 = semaforo;
    }

    public void run() {

        while (true) {
            if (GerenteDeMemoria.Particao.getOcup(0) && App.FlagCPU == false && i == 0) {
                App.FlagCPU = true;
                try {
                    Semaforo6.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runSeparate(0)) {
                    GerenteDeMemoria.cheio = false;
                    GerenteDeMemoria.Particao.setOcup(i);
                    App.FlagCPU = false;
                    i++;
                } else {
                    i++;
                    App.FlagCPU = false;
                }

                Semaforo6.release();
            }

            if (GerenteDeMemoria.Particao.getOcup(1) && App.FlagCPU == false && i == 1) {
                App.FlagCPU = true;
                try {
                    Semaforo6.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runSeparate(1)) {
                    GerenteDeMemoria.cheio = false;
                    GerenteDeMemoria.Particao.setOcup(i);
                    App.FlagCPU = false;
                    i++;
                } else {
                    App.FlagCPU = false;
                    i++;
                }

                Semaforo6.release();
            }

            if (GerenteDeMemoria.Particao.getOcup(2) && App.FlagCPU == false && i == 2) {
                App.FlagCPU = true;
                try {
                    Semaforo6.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runSeparate(2)) {
                    GerenteDeMemoria.cheio = false;
                    GerenteDeMemoria.Particao.setOcup(i);
                    App.FlagCPU = false;
                    i++;
                } else {
                    i++;
                    App.FlagCPU = false;
                }

                Semaforo6.release();
            }

            if (GerenteDeMemoria.Particao.getOcup(3) && App.FlagCPU == false && i == 3) {
                App.FlagCPU = true;
                try {
                    Semaforo6.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runSeparate(3)) {
                    GerenteDeMemoria.cheio = false;
                    GerenteDeMemoria.Particao.setOcup(i);
                    App.FlagCPU = false;
                    i++;
                } else {
                    i++;
                    App.FlagCPU = false;
                }

                Semaforo6.release();
            }

        }
    }

    public boolean runSeparate(int part) {

        pCB aux = GerenteDeMemoria.Particao.getProgram(part);

        GerenteDeMemoria.PC = aux.getSafe();
        GerenteDeMemoria.regis[0] = aux.getr0();
        GerenteDeMemoria.regis[1] = aux.getr1();
        GerenteDeMemoria.regis[2] = aux.getr2();
        GerenteDeMemoria.regis[3] = aux.getr3();
        GerenteDeMemoria.regis[4] = aux.getr4();
        GerenteDeMemoria.regis[5] = aux.getr5();
        GerenteDeMemoria.regis[6] = aux.getr6();
        GerenteDeMemoria.regis[7] = aux.getr7();

        if (GerenteDeMemoria.PC == 2000) {
            System.out.println("Thread " + part + " Finished!");
            return true;
        }

        count = 0;

        while (GerenteDeMemoria.PC != 2000 && count <= 20) {

            System.out.println("Thread: " + part + " - PC: " + GerenteDeMemoria.PC);
            // System.out.println("i: " + count); //Descomente essa linha se quiser ver o
            // número de iterações por thread
            func = GerenteDeMemoria.memoria[GerenteDeMemoria.PC].opcode;

            if (GerenteDeMemoria.memoria[GerenteDeMemoria.PC].registrador1 != null) {
                String aux2 = GerenteDeMemoria.memoria[GerenteDeMemoria.PC].registrador1.substring(1);
                regisAux = Integer.parseInt(aux2);

            }
            if (GerenteDeMemoria.memoria[GerenteDeMemoria.PC].registrador2 != null) {
                String aux1 = GerenteDeMemoria.memoria[GerenteDeMemoria.PC].registrador2.substring(1);
                regisAux1 = Integer.parseInt(aux1);

            }

            GerenteDeMemoria.PC = mapa(func, part);
        }

        aux.setSafe(GerenteDeMemoria.PC);
        aux.setr0(GerenteDeMemoria.regis[0]);
        aux.setr1(GerenteDeMemoria.regis[1]);
        aux.setr2(GerenteDeMemoria.regis[2]);
        aux.setr3(GerenteDeMemoria.regis[3]);
        aux.setr4(GerenteDeMemoria.regis[4]);
        aux.setr5(GerenteDeMemoria.regis[5]);
        aux.setr6(GerenteDeMemoria.regis[6]);
        aux.setr7(GerenteDeMemoria.regis[7]);

        GerenteDeMemoria.Particao.setProgram(aux, part);
        return false;

    }

    public int tradutor(int num, int part) {

        int toma = 0;

        switch (part) {
            case 0: {
                toma = num;
            }
                break;

            case 1: {
                toma = num + 256;
            }
                break;

            case 2: {
                toma = num + 512;
            }
                break;

            case 3: {
                toma = num + 768;
            }
                break;

            default: {
                System.out.println("Erro");
            }
        }

        return toma;

    }

    public void erase() {
        count = 1;
        GerenteDeMemoria.PC = 0;
    }

    public void JMP(int k, int part) {

        GerenteDeMemoria.PC = k;
        GerenteDeMemoria.PC = tradutor(GerenteDeMemoria.PC, part);
        count++;

    }

    public void JMPI(int RS, int part) {

        GerenteDeMemoria.PC = RS;
        GerenteDeMemoria.PC = tradutor(GerenteDeMemoria.PC, part);
        count++;

    }

    public void JMPIG(int RS, int RC, int part) {

        if (RC > 0) {
            GerenteDeMemoria.PC = RS;
            GerenteDeMemoria.PC = tradutor(GerenteDeMemoria.PC, part);
        } else
            GerenteDeMemoria.PC = GerenteDeMemoria.PC + 1;

        count++;
    }

    public void JMPIL(int RS, int RC, int part) {

        if (RC < 0) {
            GerenteDeMemoria.PC = RS;
            GerenteDeMemoria.PC = tradutor(GerenteDeMemoria.PC, part);
        } else
            GerenteDeMemoria.PC = GerenteDeMemoria.PC + 1;
        count++;

    }

    public void JMPIE(int RS, int RC, int part) {

        if (RC == 0) {
            GerenteDeMemoria.PC = RS;
            GerenteDeMemoria.PC = tradutor(GerenteDeMemoria.PC, part);
        } else
            GerenteDeMemoria.PC = GerenteDeMemoria.PC + 1;
        count++;

    }

    public void ADDI(int RD, int k) {

        GerenteDeMemoria.regis[RD] = GerenteDeMemoria.regis[RD] + k;
        count++;

    }

    public void SUBI(int RD, int k) {

        GerenteDeMemoria.regis[RD] = GerenteDeMemoria.regis[RD] - k;
        count++;

    }

    public void LDI(int RD, int k) {

        GerenteDeMemoria.regis[RD] = k;
        count++;

    }

    public void LDD(int RD, int A) {

        GerenteDeMemoria.regis[RD] = GerenteDeMemoria.memoria[A].parametro;
        count++;

    }

    public void STD(int A, int RS) {

        GerenteDeMemoria.memoria[A].parametro = GerenteDeMemoria.regis[RS];
        count++;

    }

    public void ADD(int RD, int RS) {

        GerenteDeMemoria.regis[RD] = GerenteDeMemoria.regis[RD] + GerenteDeMemoria.regis[RS];
        count++;

    }

    public void SUB(int RD, int RS) {

        GerenteDeMemoria.regis[RD] = GerenteDeMemoria.regis[RD] - GerenteDeMemoria.regis[RS];
        count++;

    }

    public void MULT(int RD, int RS) {

        GerenteDeMemoria.regis[RD] = GerenteDeMemoria.regis[RD] * GerenteDeMemoria.regis[RS];
        count++;

    }

    public void LDX(int RD, int RS) {

        int valor = GerenteDeMemoria.regis[RS];

        GerenteDeMemoria.regis[RD] = GerenteDeMemoria.memoria[valor].parametro;

        count++;

    }

    public void STX(int RD, int RS) {

        int valor = GerenteDeMemoria.regis[RD];

        GerenteDeMemoria.memoria[valor].parametro = GerenteDeMemoria.regis[RS];

        count++;
    }

    public void STOP() {

        GerenteDeMemoria.PC = 2000;
        count++;

    }

    public int mapa(String func, int part) {

        switch (func) {
            case "JMP":
                JMP(GerenteDeMemoria.memoria[GerenteDeMemoria.PC].parametro, part);
                return GerenteDeMemoria.PC;
            case "JMPI":
                JMPI(GerenteDeMemoria.regis[regisAux], part);
                return GerenteDeMemoria.PC;
            case "JMPIG":
                JMPIG(GerenteDeMemoria.regis[regisAux], GerenteDeMemoria.regis[regisAux1], part);
                return GerenteDeMemoria.PC;
            case "JMPIL":
                JMPIL(GerenteDeMemoria.regis[regisAux], GerenteDeMemoria.regis[regisAux1], part);
                return GerenteDeMemoria.PC;
            case "JMPIE":
                JMPIE(GerenteDeMemoria.regis[regisAux], GerenteDeMemoria.regis[regisAux1], part);
                return GerenteDeMemoria.PC;
            case "ADDI":
                ADDI(regisAux, GerenteDeMemoria.memoria[GerenteDeMemoria.PC].parametro);
                break;
            case "SUBI":
                SUBI(regisAux, GerenteDeMemoria.memoria[GerenteDeMemoria.PC].parametro);
                break;
            case "LDI":
                LDI(regisAux, GerenteDeMemoria.memoria[GerenteDeMemoria.PC].parametro);
                break;
            case "LDD":
                LDD(regisAux, GerenteDeMemoria.memoria[GerenteDeMemoria.PC].parametro);
                break;
            case "STD":
                STD(GerenteDeMemoria.memoria[GerenteDeMemoria.PC].parametro, regisAux);
                break;
            case "ADD":
                ADD(regisAux, regisAux1);
                break;
            case "SUB":
                SUB(regisAux, regisAux1);
                break;
            case "MULT":
                MULT(regisAux, regisAux1);
                break;
            case "LDX":
                LDX(regisAux, regisAux1);
                break;
            case "STX":
                STX(regisAux, regisAux1);
                break;
            case "STOP": {
                STOP();
                return GerenteDeMemoria.PC;
            }
            default:
                System.out.println("Função não reconhecida!");
        }

        return GerenteDeMemoria.PC + 1;

    }

}