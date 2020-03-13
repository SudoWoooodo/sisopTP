import java.util.ArrayList;
import java.io.*;

public class mv{
    private String[] Memoria;
    private int PC;
    private String[] regis = new String[7];

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

    public void executa(){

        

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

    public void P1(String positions){

        
    }

    public String getmv(int index) {

        return Memoria[index];
    }

    public void setmv(int index, String bytes) {

        Memoria[index] = bytes;

    }

     public void aumCount(){

         PC++;

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
            
              resposta[b] = linha;

              b++;
              linha = lerArq.readLine(); 
            }
       
            arq.close();
          } catch (IOException e) {
              System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
          }

          return resposta;

    }


}