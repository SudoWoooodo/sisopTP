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

// NOBUG: Deve-se encerrar e rodar novamente caso queira trocar de arquivo -> RESOLVIDO

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
            switch (le.nextLine()) {
                
                case "quit": {
                    i++;
                } break;
                    
                case "load": {
                    System.out.printf("Informe o nome de arquivo texto:\n");
                    String nome = le.nextLine();
                    System.out.printf("Informe em que parte da memoria gostaria de alocar(1 a 4):\n");
                    int part = le.nextInt();
                    Beta.loadArquivo(nome, part);
                } break;
                    
                case "sit": {
                    System.out.println("Qual parte?");
                    Beta.sit(le.nextInt());
                } break;

                case "run":{
                    Beta.runAll();
                } break;
                    
                case "runs":{
                    System.out.println("Digite a particao:");
                    int nome = le.nextInt();
                    Beta.runSeparate(nome);
                } break;           

                case "countdown":{
                    Beta.erase();
                } break;
                    
                case "clear":{
                    Beta.clearData();
                } break; 

                //default:{
                //System.out.println("Erro");
                //} break;
                    
            } 
        }
    }
}
