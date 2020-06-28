package src;

public class gerenciaDeMemoria{

    private int id, posInicial, posFinal, safe, r0, r1, r2, r3, r4, r5, r6, r7, ocupado;

        public gerenciaDeMemoria(int id, int posInicial, int posFinal, int safe, int r0, int r1, int r2, int r3, int r4, int r5, int r6, int r7, int ocupado){
            this.id = id;
            this.posInicial = posInicial;
            this.posFinal = posFinal;
            this.safe = safe;
            this.r0 = r0;
            this.r1 = r1;
            this.r2 = r2;
            this.r3 = r3;
            this.r4 = r4;
            this.r5 = r5;
            this.r6 = r6;
            this.r7 = r7;
            this.ocupado = ocupado;
        }

        public int getId(){
            return this.id;
        }

        public int getPosInicial(){
            return this.posInicial;
        }

        public int getPosFinal(){
            return this.posFinal;
        }

        public int getr0(){
            return this.r0;
        }

        public int getr1(){
            return this.r1;
        }

        public int getr2(){
            return this.r2;
        }

        public int getr3(){
            return this.r3;
        }

        public int getr4(){
            return this.r4;
        }

        public int getr5(){
            return this.r5;
        }

        public int getr6(){
            return this.r6;
        }

        public int getr7(){
            return this.r7;
        }

        public int getSafe(){
            return this.safe;
        }

        public void setSafe(int x){
            this.safe = x;
        }

        public void setr0(int x){
            this.r0 = x;
        }

        public void setr1(int x){
            this.r1 = x;
        }
        
        public void setr2(int x){
            this.r2 = x;
        }

        public void setr3(int x){
            this.r3 = x;
        }

        public void setr4(int x){
            this.r4 = x;
        }

        public void setr5(int x){
            this.r5 = x;
        }

        public void setr6(int x){
            this.r6 = x;
        }

        public void setr7(int x){
            this.r7 = x;
        }

        public int getOcup(){
            return this.ocupado;
        }

        public void setOcup(int x){
            this.ocupado = x;
        }
    }

