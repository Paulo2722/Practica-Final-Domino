package Domino.Juego;

public class Ficha {
    private int ladoA;
    private int ladoB;

    public Ficha(int ladoA, int ladoB){
        this.ladoA = ladoA;
        this.ladoB = ladoB;
    }

    public int getLadoA() {
        return ladoA;
    }

    public int getLadoB() {
        return ladoB;
    }

    public void setLadoA(int ladoA) {
        this.ladoA = ladoA;
    }

    public void setLadoB(int ladoB) {
        this.ladoB = ladoB;
    }

    @Override
    public String toString(){
        return "[" + ladoA + "|" + ladoB + "]";
    }

    public void girarFicha(){
        int temp = ladoA;
        ladoA = ladoB;
        ladoB = temp;
    }
}
