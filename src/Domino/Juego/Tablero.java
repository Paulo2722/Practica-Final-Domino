package Domino.Juego;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final int filas = 10;
    private final int columnas = 10;
    private Ficha[][] tablero;
    private List<Ficha> fichas;

    public Tablero() {
        this.fichas = crearFichas();
        tablero = new Ficha[filas][columnas];
    }

    public List<Ficha> crearFichas(){
        List<Ficha> fichas = new ArrayList<>();

        for (int i = 0; i <= 6; i++){
            for (int j = i; j <= 6; j++){
                fichas.add(new Ficha(i,j));
            }
        }
        return fichas;
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
