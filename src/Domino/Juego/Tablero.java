package Domino.Juego;

public class Tablero {
    private final int filas = 10;
    private final int columnas = 10;
    private Ficha[][] tablero;

    public Tablero() {
        tablero = new Ficha[filas][columnas];
    }

    public void imprimirTablero(){
        for (int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                System.out.println(tablero[i][j].toString());
            }
            System.out.println();
        }
    }
}
