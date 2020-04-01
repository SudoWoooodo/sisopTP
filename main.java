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
            }
        }
    }
}