// Autores: Pedro Portella, Rafael Resende e Lucas Tashan

import java.util.ArrayList;
import java.io.*;

// Classe do Processador, onde é feita toda execução dos programas na máquina
// O primeiro método utilizado nessa classe é o construtor public mv
// Depois dele é o load arquivo (no final da classe)

public class mv extends gerenciaDeMemoria{
    private int PC;
    private int count;
    private posicaoDeMemoria[] memoria;
    private Integer[] regis = new Integer[8];
    ArrayList<posicaoDeMemoria> programa;
    int regisAux;
    int regisAux1;
    String func;

    // Onde é criado o array da memória e populado de objetos com atributos nulos
    // Tambem é criado o array de registradores
    public mv() {

        memoria = new posicaoDeMemoria[1024];
        for (int k = 0; k < memoria.length; k++) {
            memoria[k] = new posicaoDeMemoria(null, null, null, -99);
        }
        PC = 0;
        for (int i = 0; i < regis.length; i++) {

            regis[i] = 0;
        }

    }

    // Metodo criado para visualização de todas posições de memória e registradores
    public void sit() {

        int c = 0;
        while (c < memoria.length) {
            if (memoria[c] != null) {
                System.out.println(memoria[c] + " na posicao " + c);
            }
            c++;
        }

        c = 0;

        while (c < regis.length) {

            System.out.println("Registrador: " + c + " = " + regis[c]);
            c++;
        }

    }

    // Metodo utilizado para identificar a chamar a função correta de acordo com seu
    // opcode
    public int mapa(String func) {

        switch (func) {
            case "JMP":
                JMP(memoria[PC].parametro);
                return PC;
            case "JMPI":
                JMPI(regis[regisAux]);
                return PC;
            case "JMPIG":
                JMPIG(regis[regisAux], regis[regisAux1]);
                return PC;
            case "JMPIL":
                JMPIL(regis[regisAux], regis[regisAux1]);
                return PC;
            case "JMPIE":
                JMPIE(regis[regisAux], regis[regisAux1]);
                return PC;
            case "ADDI":
                ADDI(regisAux, memoria[PC].parametro);
                break;
            case "SUBI":
                SUBI(regisAux, memoria[PC].parametro);
                break;
            case "LDI":
                LDI(regisAux, memoria[PC].parametro);
                break;
            case "LDD":
                LDD(regisAux, memoria[PC].parametro);
                break;
            case "STD":
                STD(memoria[PC].parametro, regisAux);
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
                System.out.println(count);
                return PC;
            }
            default:
                System.out.println("Função não reconhecida!");
        }

        return PC + 1;

    }

    // Metodo principal da classe e é utilizado para executar o loop que fica lendo
    // as linhas do programa
    // A cada linha lida, ele faz o tratamento devido do opcode, registradores e
    // parametros
    // Em seguida chama o método que trata as funções dos opcodes que está logo
    // acima


    public void run() { //roda uma particicao, recebe uma, 20 vezes-- colocar num metodo maior que rode as 4 em sequencia
        //while count < 20

        while (PC != 2000) {
            func = memoria[PC].opcode;

            if (memoria[PC].registrador1 != null) {
                String aux = memoria[PC].registrador1.substring(1);
                regisAux = Integer.parseInt(aux);

            }
            if (memoria[PC].registrador2 != null) {
                String aux1 = memoria[PC].registrador2.substring(1);
                regisAux1 = Integer.parseInt(aux1);

            }
            
            PC = mapa(func);
        }

        System.out.println("\n");
        System.out.println("Resposta na memória: " + count);
        System.out.println("\n");
        for (int i = programa.size(); i < memoria.length; i++) {
            if (memoria[i].parametro != -99) {
                System.out.println(memoria[i].getParametro());
            }
        }

    }

    public void erase(){
        count = 0;
    }

    public void JMP(int k) {

        PC = k;
        count++;

    }

    public void JMPI(int RS) {

        PC = RS;
        count++;

    }

    public void JMPIG(int RS, int RC) {

        if (RC > 0) {
            PC = RS;
        } else
            PC = PC + 1;

            count++;
    }

    public void JMPIL(int RS, int RC) {

        if (RC < 0) {
            PC = RS;
        } else
            PC = PC + 1;
            count++;

    }

    public void JMPIE(int RS, int RC) {

        if (RC == 0) {
            PC = RS;
        } else
            PC = PC + 1;
            count++;

    }

    public void ADDI(int RD, int k) {

        regis[RD] = regis[RD] + k;
        count++;

    }

    public void SUBI(int RD, int k) {

        regis[RD] = regis[RD] - k;
        count++;

    }

    public void LDI(int RD, int k) {

        regis[RD] = k;
        count++;

    }

    public void LDD(int RD, int A) {

        regis[RD] = memoria[A].parametro;
        count++;

    }

    public void STD(int A, int RS) {

        memoria[A].parametro = regis[RS];
        count++;

    }

    public void ADD(int RD, int RS) {

        regis[RD] = regis[RD] + regis[RS];
        count++;

    }

    public void SUB(int RD, int RS) {

        regis[RD] = regis[RD] - regis[RS];
        count++;

    }

    public void MULT(int RD, int RS) {

        regis[RD] = regis[RD] * regis[RS];
        count++;

    }

    public void LDX(int RD, int RS) {

        int valor = regis[RS];

        regis[RD] = memoria[valor].parametro;

        count++;

    }

    public void STX(int RD, int RS) {

        int valor = regis[RD];

        memoria[valor].parametro = regis[RS];

        count++;
    }

    public void STOP() {

        PC = 2000;
        count++;

    }

    // Método que faz a leitura do arquivo e o tratamento inicial do texto
    // Ele que separa e identifica o que é string, registrador, parametro, int,
    // opcode, etc

    public ArrayList<posicaoDeMemoria> loadArquivo(String nome) {

        int a = nome.length();
        programa = new ArrayList<>();

        if (nome.charAt(a - 1) != 't' || nome.charAt(a - 2) != 'x' || nome.charAt(a - 3) != 't'
                || nome.charAt(a - 4) != '.') {

            nome = nome + ".txt";
        }

        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            
            while (linha != null) {
                
                // Array como estrutura de dados auxiliar para splitar cada pedaço lido da linha
                // Podendo ser (opcode, registradores ou parametros)
                
                String[] strArray = linha.split("[,' ']");     
                    

                // Utilização de mais uma estrutura auxiliar (ArrayList) que pega esses pedaços
                // splitados
                // do array declarado logo acima e instancia objetos da classe posicaoDeMemoria
                // pra passar isso tudo para o Array de memoria em seguida
                if (strArray.length == 1) {
                    posicaoDeMemoria line = new posicaoDeMemoria(strArray[0], null, null, -99);
                    programa.add(line);
                } else if (strArray.length == 2) {
                    if (strArray[1].charAt(0) == 'r') {
                        posicaoDeMemoria line = new posicaoDeMemoria(strArray[0], strArray[1], null, -99);
                        programa.add(line);
                    } else {
                        int castInt = Integer.parseInt(strArray[1]);
                        posicaoDeMemoria line = new posicaoDeMemoria(strArray[0], castInt, null);
                        programa.add(line);
                    }
                } else if (strArray.length == 3) {
                    if (strArray[1].charAt(0) == 'r' && strArray[2].charAt(0) == 'r') {
                        posicaoDeMemoria line = new posicaoDeMemoria(strArray[0], strArray[1], strArray[2], -99);
                        programa.add(line);
                    } else if (strArray[1].charAt(0) == 'r' && strArray[2].charAt(0) != 'r') {
                        int castInt = Integer.parseInt(strArray[2]);
                        posicaoDeMemoria line = new posicaoDeMemoria(strArray[0], strArray[1], castInt);
                        programa.add(line);
                    } else if (strArray[1].charAt(0) != 'r' && strArray[2].charAt(0) == 'r') {
                        int castInt = Integer.parseInt(strArray[1]);
                        posicaoDeMemoria line = new posicaoDeMemoria(strArray[0], castInt, strArray[2]);
                        programa.add(line);
                    }

                } else {
                    System.out.println("Erro na linha!");
                }

                linha = lerArq.readLine();
            }

            System.out.println("Arquivo carregado na memoria.");
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // População da Memoria com os objetos criados no ArrayList
        for (int i = 0; i < programa.size(); i++) {
            memoria[i] = programa.get(i);
        }

        return programa;

    }

}