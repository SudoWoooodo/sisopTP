import java.util.*;

public class main{
    public static void main(String[] args) {

        Scanner le = new Scanner(System.in);
        int i = 0;
        mv Beta = new mv();

        while(i == 0){
            System.out.println("Ola!");
            String resposta = le.nextLine();
            switch(resposta){
                case "quit":{
                    i++;
                } break;
                case "load":{
                    System.out.printf("Informe o nome de arquivo texto:\n");
                    String nome = le.nextLine();
                    Beta.loadArquivo(nome);
                } break;
                case "situ":{
                    Beta.situ();
                } break;
                case "run":{
                    Beta.run();
                }
            }
        }
    }
}