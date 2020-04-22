// Autores: Pedro Portella, Rafael Resende e Lucas Tashan

import java.util.*;

// Classe do shell interativo
// Roda a string "Escutando comandos: " a cada execução de script correta
// Temos 3 opções de scripts:
// Quit (para encerrar o programa)
// Load (para carregar um arquivo) 
// OBS: Voce deverá informar o nome do arquivo com ou sem a extesão depois de rodar Load
// Sit (para verificar o status da memória e registradores)
// Run (para rodar o arquivo carregado)

// BUG: Deve-se encerrar e rodar novamente caso queira trocar de arquivo

// Sugestão de fluxo comandos:
// load
// (informa o nome do arquivo)
// run

public class main {
    public static void main(String[] args) {

        Scanner le = new Scanner(System.in);
        int i = 0;
        mv Beta = new mv();

        while (i == 0) {
            System.out.println("Escutando comandos:");
            String resposta = le.nextLine();
            switch (resposta) {
                case "quit": {
                    i++;
                }
                    break;
                case "load": {
                    System.out.printf("Informe o nome de arquivo texto:\n");
                    String nome = le.nextLine();
                    Beta.loadArquivo(nome);
                }
                    break;
                case "sit": {
                    Beta.sit();
                }
                    break;
                case "run": {
                    Beta.run();
                }
                case "help":{
                    Beta.erase();
                }
            }
        }
    }
}


// O que temos?

// main.java
    //Arquivo responsavel pelo shell (ele que carrega arquivos e que roda a CPU)

// mv.java
    //Arquivo correspondente a CPU (onde é criada e caregada a memória, e feita toda logica dos programas)

//posicaoDeMemoria.java
    //Arquivo criado para construir a memória

//Devemos implementar:

//  Gerencia de Memoria
    // posicaoDeMemoria [1024] memoria -> particoes [2014] memoria (particoes que ocupam pedaços da memoria)
    // Criação da memória e carregamento da memória

//  Gerencia de Processos
    // Criar uma fila de processos (que vão ser as partições ocupadas) e rodar 20 instruções de cada um, um por vez
    // necessidade de guardar os status de cada um deles a cada fim de execução e colocar pro final da fila

    // Teremos toda a estrutura necessária da mv para cada processo (8 registradores, a partição de memória)
    //  2 registradores extras que dirão o inicio e fim da partição

    // OBS: Implementar um tradutor de memória (arrumar conforme a partição que está sendo usada)