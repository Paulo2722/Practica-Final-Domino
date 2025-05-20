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
    private List<Parejas> parejas;

    public Tablero() {
        this.fichas = crearFichas();
        this.jugadores = new ArrayList<>();
        this.parejas = new ArrayList<>();
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

    public void crearParejas(){
        for (int i = 0; i < jugadores.size(); i += 2){
            Parejas pareja = new Parejas("Pareja " + ((i / 2) + 1));

            pareja.agregarJugador(jugadores.get(i));
            if (i + 1 < jugadores.size()) {
                pareja.agregarJugador(jugadores.get(i + 1));
            }
            parejas.add(pareja);

            System.out.print(pareja.getNombre() + ": ");
            List<Jugador> integrantes = pareja.getJugadoresPareja();
            for (int j = 0; j < integrantes.size(); j++) {
                System.out.print(integrantes.get(j).getNombre() + " ");
            }
            System.out.println();
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
    }

    public void robarFichas(Jugador jugador){
        if (!fichas.isEmpty()){
            Random random = new Random();
            Ficha robarFicha = fichas.remove(random.nextInt(fichas.size()));
            jugador.recibirFicha(robarFicha);
            System.out.println("Has robado la ficha " + robarFicha);
        }else{
            System.out.println("No quedan fichas para robar");
        }
    }

    public void imprimirTablero(){
        for (int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                System.out.println(tablero[i][j].toString());
            }
            System.out.println();
        }
    }

    public int modoDeJuego() {
        Scanner sc = new Scanner(System.in);
        int respuesta;

        while (true) {
            System.out.println("Elige el modo de juego:");
            System.out.println("Pulsa 1 si quieres el modo individual, pulsa 2 si quieres el modo en parejas");
            try {
                respuesta = sc.nextInt();
                if (respuesta == 1 || respuesta == 2) {
                    return respuesta;
                }
            } catch (Exception e) {
                System.out.print("El valor introducido no es valido, introduce un nuevo valor");
            }
        }
    }

    public int horientacionFicha(){
        Scanner sc = new Scanner(System.in);
        int respuesta;

        while (true) {
            System.out.println("Elige la colocacion de la pieza:");
            System.out.println("Pulsa 1 si quieres colocarla de forma vertical, pulsa 2 para colocarla de forma horizontal");
            try {
                respuesta = sc.nextInt();
                if (respuesta == 1 || respuesta == 2) {
                    return respuesta;
                }
            } catch (Exception e) {
                System.out.print("El valor introducido no es valido, introduce un nuevo valor");
            }
        }
    }
}
