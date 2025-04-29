package Domino.Juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tablero {
    private final int filas = 10;
    private final int columnas = 10;
    private Ficha[][] tablero;
    private List<Ficha> fichas;
    private List<Jugador> jugadores;

    public Tablero() {
        this.fichas = crearFichas();
        this.jugadores = new ArrayList<>();
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

    public void crearJugadores(int numeroJugadores) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 1; i <= numeroJugadores; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            String nombre = scanner.nextLine();
            jugadores.add(new Jugador(nombre));
        }
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void repartirFichas(int numeroJugadores){
        int numeroMaximoFichasPorJugador = 7;
        Random random = new Random();

        for (int i = 0; i < numeroMaximoFichasPorJugador; i++){
            for (int j = 0; j < numeroJugadores; j++){
                int numeroAleatorio = random.nextInt(fichas.size());
                Ficha fichaIndice = fichas.remove(numeroAleatorio);
                jugadores.get(j).recibirFicha(fichaIndice);
            }
        }
        List<Ficha> fichasSinRepartir = fichas;
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
