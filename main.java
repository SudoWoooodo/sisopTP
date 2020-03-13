import java.util.*;

public class main{
    public static void main(String[] args) {
        Scanner le = new Scanner(System.in);
        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = le.nextLine();

        mv Beta = new mv();
        Beta.loadArquivo(nome);
    }
}