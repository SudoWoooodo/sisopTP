// Autores: Pedro Portella, Rafael Resende e Lucas Tashan

import java.util.ArrayList;
import java.io.*;

// Classe do Processador, onde é feita toda execução dos programas na máquina
// O primeiro método utilizado nessa classe é o construtor public mv
// Depois dele é o load arquivo (no final da classe)

public class mv{
    protected int PC;
    protected int count;
    protected posicaoDeMemoria[] memoria;
    private Integer[] regis = new Integer[8];
    ArrayList<posicaoDeMemoria> programa;
    int regisAux;
    int regisAux1;
    String func;

    //Cria as 4 partições
    gerenciaDeMemoria c1 = new gerenciaDeMemoria(0, 0, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0); 
    gerenciaDeMemoria c2 = new gerenciaDeMemoria(1, 256, 511, 256, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    gerenciaDeMemoria c3 = new gerenciaDeMemoria(2, 512, 767, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    gerenciaDeMemoria c4 = new gerenciaDeMemoria(3, 768, 1023, 768, 0, 0, 0, 0, 0, 0, 0, 0, 0);

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

    // Metodo criado para visualização de todas posições de memória e registradores a partir de uma posição desejada
    public void sit(int x) {

        int c = 0;
        int jonas = 0;
        if(x == 2){c = 256;}
        if(x == 3){c = 512;}
        if(x == 4){c = 768;}

        while (jonas < 256) {
            if (memoria[c] != null) {
                System.out.println(memoria[c] + " na posicao " + c);
            }
            jonas++;
            c++;
        }

        c = 0;
    }

    //Limpa a memória inteira
    public void clearData(){

        for (int k = 0; k < memoria.length; k++) {
            memoria[k] = new posicaoDeMemoria(null, null, null, -99);
        }
        PC = 0;
        for (int i = 0; i < regis.length; i++) {

            regis[i] = 0;
        }
    }

    // Metodo utilizado para identificar a chamar a função correta de acordo com seu
    // opcode
    public int mapa(String func, int part) {

        switch (func) {
            case "JMP":
                JMP(memoria[PC].parametro, part);
                return PC;
            case "JMPI":
                JMPI(regis[regisAux], part);
                return PC;
            case "JMPIG":
                JMPIG(regis[regisAux], regis[regisAux1], part);
                return PC;
            case "JMPIL":
                JMPIL(regis[regisAux], regis[regisAux1], part);
                return PC;
            case "JMPIE":
                JMPIE(regis[regisAux], regis[regisAux1], part);
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

    //Método que realiza a execução de todas as partes (se houver algo para executar), cada uma sendo realizada 20 vezes.
    public void runAll(){

        while(c1.getSafe() + c2.getSafe() + c3.getSafe() + c4.getSafe() != 8000 ){ 

            if(c1.getOcup() == 1){
                runSeparate(1);
            } else c1.setSafe(2000);

            if(c2.getOcup() == 1){
                runSeparate(2);
            } else c2.setSafe(2000);

            if(c3.getOcup() == 1){
                runSeparate(3);
            } else c3.setSafe(2000);

            if(c4.getOcup() == 1){
                runSeparate(4);
            } else c4.setSafe(2000);
        }

    }

    //Método que realiza a execução de uma partição de memória 20x ou até ela acabar
    public void runSeparate(int part){
        
        erase();
        PC = tradutor(PC, part);

                switch(part){
                    case 1:{
                        PC = c1.getSafe();
                        regis[0] = c1.getr0();
                        regis[1] = c1.getr1();
                        regis[2] = c1.getr2();
                        regis[3] = c1.getr3();
                        regis[4] = c1.getr4();
                        regis[5] = c1.getr5();
                        regis[6] = c1.getr6();
                        regis[7] = c1.getr7();
                    } break;
                    case 2:{
                        PC = c2.getSafe();
                        regis[0] = c2.getr0();
                        regis[1] = c2.getr1();
                        regis[2] = c2.getr2();
                        regis[3] = c2.getr3();
                        regis[4] = c2.getr4();
                        regis[5] = c2.getr5();
                        regis[6] = c2.getr6();
                        regis[7] = c2.getr7();
                    } break;
                    case 3:{
                        PC = c3.getSafe();
                        regis[0] = c3.getr0();
                        regis[1] = c3.getr1();
                        regis[2] = c3.getr2();
                        regis[3] = c3.getr3();
                        regis[4] = c3.getr4();
                        regis[5] = c3.getr5();
                        regis[6] = c3.getr6();
                        regis[7] = c3.getr7();
                    } break;
                    case 4:{
                        PC = c4.getSafe();
                        regis[0] = c4.getr0();
                        regis[1] = c4.getr1();
                        regis[2] = c4.getr2();
                        regis[3] = c4.getr3();
                        regis[4] = c4.getr4();
                        regis[5] = c4.getr5();
                        regis[6] = c4.getr6();
                        regis[7] = c4.getr7();
                    } break;
                }
                
                
                while (PC != 2000 && count <= 20) {
                    
                    System.out.println(PC);
                    System.out.println(count);
                    func = memoria[PC].opcode;
        
                    if (memoria[PC].registrador1 != null) {
                        String aux = memoria[PC].registrador1.substring(1);
                        regisAux = Integer.parseInt(aux);
        
                    }
                    if (memoria[PC].registrador2 != null) {
                        String aux1 = memoria[PC].registrador2.substring(1);
                        regisAux1 = Integer.parseInt(aux1);
        
                    }
                    
                    PC = mapa(func, part);
                }
                
                switch(part){
                    case 1:{
                        c1.setSafe(PC);
                        c1.setr0(regis[0]);
                        c1.setr1(regis[1]);
                        c1.setr2(regis[2]);
                        c1.setr3(regis[3]);
                        c1.setr4(regis[4]);
                        c1.setr5(regis[5]);
                        c1.setr6(regis[6]);
                        c1.setr7(regis[7]);
                    } break;
                    case 2:{
                        c2.setSafe(PC);
                        c2.setr0(regis[0]);
                        c2.setr1(regis[1]);
                        c2.setr2(regis[2]);
                        c2.setr3(regis[3]);
                        c2.setr4(regis[4]);
                        c2.setr5(regis[5]);
                        c2.setr6(regis[6]);
                        c2.setr7(regis[7]);
                    } break;
                    case 3:{
                        c3.setSafe(PC);
                        c3.setr0(regis[0]);
                        c3.setr1(regis[1]);
                        c3.setr2(regis[2]);
                        c3.setr3(regis[3]);
                        c3.setr4(regis[4]);
                        c3.setr5(regis[5]);
                        c3.setr6(regis[6]);
                        c3.setr7(regis[7]);
                    } break;
                    case 4:{
                        c4.setSafe(PC);
                        c4.setr0(regis[0]);
                        c4.setr1(regis[1]);
                        c4.setr2(regis[2]);
                        c4.setr3(regis[3]);
                        c4.setr4(regis[4]);
                        c4.setr5(regis[5]);
                        c4.setr6(regis[6]);
                        c4.setr7(regis[7]);
                    } break;
                }
    }

    public void erase(){
        count = 1;
        PC = 0;
    }

    public void JMP(int k, int part) {

        PC = k;
        PC = tradutor(PC, part);
        count++;

    }

    public void JMPI(int RS, int part) {

        PC = RS;
        PC = tradutor(PC, part);
        count++;

    }

    public void JMPIG(int RS, int RC, int part) {

        if (RC > 0) {
            PC = RS;
            PC = tradutor(PC, part);
        } else
            PC = PC + 1;

            count++;
    }

    public void JMPIL(int RS, int RC, int part) {

        if (RC < 0) {
            PC = RS;
            PC = tradutor(PC, part);
        } else
            PC = PC + 1;
            count++;

    }

    public void JMPIE(int RS, int RC, int part) {

        if (RC == 0) {
            PC = RS;
            PC = tradutor(PC, part);
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

    //"Traduz" a memória para a partição desejada
    public int tradutor(int num, int part){

        int toma = 0;

        switch(part){
            case 1:{
                toma = num;
            } break;
                
            case 2:{
                toma = num + 256;
            } break;

            case 3:{
                toma = num + 512;
            } break;

            case 4:{
                toma = num + 768;
            } break;

            default:{
                System.out.println("Erro");
            }
        }

        return toma;

    }

    // Método que faz a leitura do arquivo e o tratamento inicial do texto
    // Ele que separa e identifica o que é string, registrador, parametro, int,
    // opcode, etc

    public void loadArquivo(String nome, int part) {

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

            
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        //Verifica se o programa não é maior que a partição
        if(programa.size() > 256){
            programa.clear();
            System.out.println("Seu programa contém mais de 256 linhas.");
        }

        // População da Memoria com os objetos criados no ArrayList
        switch(part){
            case 1:{
                for (int i = 0; i < programa.size(); i++) {
                    memoria[i] = programa.get(i);
                }
                c1.setOcup();
                System.out.println("Arquivo carregado na memoria.");
            } break;
            case 2:{
                for (int i = 0; i < programa.size(); i++) {
                    int c = tradutor(i, part);
                    memoria[c] = programa.get(i);
                }
                c2.setOcup();
                System.out.println("Arquivo carregado na memoria.");
            } break;
            case 3:{
                for (int i = 0; i < programa.size(); i++) {
                    int c = tradutor(i, part);
                    memoria[c] = programa.get(i);
                }
                c3.setOcup();
                System.out.println("Arquivo carregado na memoria.");
            } break;
            case 4:{
                for (int i = 0; i < programa.size(); i++) {
                    int c = tradutor(i, part);
                    memoria[c] = programa.get(i);
                }
                c4.setOcup();
                System.out.println("Arquivo carregado na memoria.");
            } break;
            default:{
                System.out.println("Não foi possivel carregar o programa na memoria, por favor escolha uma particao valida.");
            } break;
        }
        

    }

}