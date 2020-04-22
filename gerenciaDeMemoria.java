
public class gerenciaDeMemoria{

    private static final int size = 1024;
    private particao[] particoes = new particao[]{
        new particao(0, 0, 255, 0),
        new particao(1, 256, 511, 0),
        new particao(2, 512, 767, 0),
        new particao(3, 768, 1023, 0)
    };
    private static int ocupado = 0;

    public class particao{
        private int id, posInicial, posFinal, safe;

        public particao(int id, int posInicial, int posFinal, int safe){
            this.id = id;
            this.posInicial = posInicial;
            this.posFinal = posFinal;
            this.safe = safe;
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

        public int getSafe(){
            return this.safe;
        }

        public void setSafe(int x){
            this.safe = x;
        }
    }

}