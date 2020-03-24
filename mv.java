import java.util.ArrayList;
import java.io.*;

public class mv{
    private String[] Memoria;
    private int PC;
    private String[] regis = new String[8];

    public mv(){

        Memoria = new String[1024];
        PC = 0;
        for(int i = 0; i < regis.length; i++){ //inicia os registradores

            regis[i] = "0";
        }

    }

    public int calculaFibo(int n){ // calcula o "n-ésimo" numero de fibo

        if(n == 1 || n == 0){
            return 1;
        }  else

        return  calculaFibo(n - 1) + calculaFibo(n - 2);

    }

    public void situ(){

        int c = 0;
        while( c < Memoria.length ){
            if(Memoria[c] != null){
            System.out.println(Memoria[c] + " na posicao " + c);
            }
            c++;
        }

        c = 0;

        while( c < regis.length){

            System.out.println("Registrador: " + c + " = " + regis[c]);
            c++;
        }

    }

    public void run(){

        String auxx = Memoria[PC];
        String func = "";
        String arg1 = "";
        String arg2 = "";
        String arg3 = "";
        int i = 0;

        String[] srtArray = auxx.split("[,' ']");

        if (srtArray.length == 3){ //aqui da pra ver quantos args tem na linha pq cada arg ta em uma posicao do array
            func = srtArray[0];
            arg1 = srtArray[1];
            arg2 = srtArray[2];
        } else if (srtArray.length == 4){
            func = srtArray[0];
            arg1 = srtArray[1];
            arg2 = srtArray[2];
            arg3 = srtArray[3];
        } else {
            System.out.println("Erro na linha!");
        }

        //se for registrador ve qual o int do regis
        
        switch (func){
            case "JMP": System.out.println("Função ADD"); //chamar a funcao add
            break;
            case "JMPI": System.out.println("Função ADD");
            break;
            case "JMPIG": System.out.println("Função ADD");
            break;
            case "JMPIL": System.out.println("Função ADD");
            break;
            case "JMPIE": System.out.println("Função ADD");
            break;
            case "ADDI": System.out.println("Função ADD");
            break;
            case "SUBI": System.out.println("Função ADD");
            break;
            case "ANDI": System.out.println("Função ADD");
            break;
            case "ORI": System.out.println("Função ADD");
            break;
            case "LDI": System.out.println("Função ADD");
            break;
            case "LDD": System.out.println("Função ADD");
            break;
            case "STD": System.out.println("Função ADD");
            break;
            case "ADD": {

                // this.ADD(arg1, arg2);

                

            }
            break;
            default: System.out.println("Função não reconhecida!");
        }
        
    }

    public void JMP(int k){

        PC = k;
    }

    public void JMPI(int RS){

        PC = Integer.parseInt(regis[RS]);

    }

    public void JMPIG(int RS, int RC){

        if(RC > 0){
            PC = Integer.parseInt(regis[RS]);
        } else PC = PC + 1;

    }

    public void JMPIL(int RS, int RC){

        if(RC < 0){
            PC = Integer.parseInt(regis[RS]);
        } else PC = PC + 1;

    }

    public void JMPIE(int RS, int RC){

        if(RC == 0){
            PC = Integer.parseInt(regis[RS]);
        } else PC = PC + 1;

    }

    public void ADDI(int RD, int k){

        int aux = Integer.parseInt(regis[RD]) + k;
        regis[RD] = Integer.toString(aux);

    }

    public void SUBI(int RD, int k){

        int aux = Integer.parseInt(regis[RD]) - k;
        regis[RD] = Integer.toString(aux);

    }

    public void ANDI(int RD, boolean k){

        boolean aux = Boolean.parseBoolean(regis[RD]) && k; 
        regis[RD] = Boolean.toString(aux);

    }

    public void ORI(int RD, boolean k){

        boolean aux = Boolean.parseBoolean(regis[RD]) || k; 
        regis[RD] = Boolean.toString(aux);
    }

    public void LDI(int RD, String k){
        
        regis[RD] = k;

    }

    public void LDD(int RD, String A){

        //recebe posição da memoria do A

    }

    public void STD(int A, int RS){

        regis[A] = regis[RS];

    }

    public void ADD(int RD, int RS){

        regis[RD] = regis[RD] + RS; //checkar

    }

    public String getmv(int index) {

        return Memoria[index];
    }

    public void setmv(int index, String bytes) {

        Memoria[index] = bytes;

    }

    public String getRegis(int r) {
         
        return regis[r];
    
    }

    public void setRegis(int r, String valor) {

        regis[r] = valor;

    }

    public String[] loadArquivo(String nome){ // le o arquivo linha a linha

        int a = nome.length();
        int b = 0;
        String[] resposta = new String[1024];

        if(nome.charAt(a - 1) != 't' || nome.charAt(a - 2) != 'x' || nome.charAt(a -3) != 't' || nome.charAt(a - 4) != '.'){

            nome = nome + ".txt";
        }

        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);
       
            String linha = lerArq.readLine(); 
            while (linha != null) {
            
              Memoria[b] = linha;

              b++;
              linha = lerArq.readLine(); 
            }

            System.out.println("Arquivo carregado na memoria.");
            arq.close();
          } catch (IOException e) {
              System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
          }

          return resposta;

    }


}