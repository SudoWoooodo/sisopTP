package src;

public class Particoes {
    
    private boolean[] parts = new boolean[4];
    private pCB[] Programs = new pCB[4];

    public Particoes(){

        this.parts[0] = false;
        this.parts[1] = false;
        this.parts[2] = false;
        this.parts[3] = false;
        this.Programs[0] = null;
        this.Programs[1] = null;
        this.Programs[2] = null;
        this.Programs[3] = null;

    }

    public boolean getOcup(int part){
        return this.parts[part];
    }

    public void setOcup(int part){
        this.parts[part] = true;
    }

    public pCB getProgram(int part){
        return this.Programs[part];
    }

    public void setProgram(pCB program, int part){
        this.Programs[part] = program;
    }

    public int traduz(int part){

        if (part == 1) {return 255;}
        if (part == 2) {return 512;}
        if (part == 3) {return 768;}
        return 0;
    }

}