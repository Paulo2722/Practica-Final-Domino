package Domino.Juego;

import Domino.Reglas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tablero {
    private final int filas = 10;
    private final int columnas = 10;
    Reglas reglas;
    private Ficha[][] tablero;
    private List<Ficha> fichas;
    private List<Jugador> jugadores;
    private List<Parejas> parejas;

    public Tablero() {
        this.fichas = crearFichas();
        this.jugadores = new ArrayList<>();
        this.parejas = new ArrayList<>();
        tablero = new Ficha[filas][columnas];

        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                tablero[i][j] = null;
            }
        }
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
        Scanner sc = new Scanner(System.in);
        String respuesta;

        System.out.println("¿Quieres robar una ficha? (S/N)");
        respuesta = sc.nextLine().toUpperCase();

        if (respuesta.equals("N")){
            return;
        }

        if (!fichas.isEmpty() && respuesta.equals("S")){
            Random random = new Random();
            Ficha robarFicha = fichas.remove(random.nextInt(fichas.size()));
            jugador.recibirFicha(robarFicha);

            System.out.println("Has robado la ficha " + robarFicha);
        }else{
            System.out.println("No quedan fichas para robar");
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] != null) {
                    System.out.print(tablero[i][j].toString());
                }else{
                    System.out.print("[ ]");
                }
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

    public void elegirReglas(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona con que reglamento quieres jugar (1-7):");
        System.out.println("1. Reglas Españolas");
        System.out.println("2. Reglas Mexicanas");
        System.out.println("3. Reglas Chilenas");
        System.out.println("4. Reglas Latinas");
        System.out.println("5. Reglas Colombianas");
        System.out.println("6. Reglas Ponceñas");
        System.out.println("7. Reglas Venezolanas");

        int opcion = sc.nextInt();

        switch (opcion){
            case 1:
                System.out.println("Has elegido las reglas Españolas");
                reglas = new Reglas_Espanyol();
                break;
            case 2:
                System.out.println("Has elegido las reglas Mexicanas");
                reglas = new Reglas_Mexicano();
                break;
            case 3:
                System.out.println("Has elegido las reglas Chilenas");
                reglas = new Reglas_Chileno();
                break;
            case 4:
                System.out.println("Has elegido las reglas Latinas");
                reglas = new Reglas_Latin();
                break;
            case 5:
                System.out.println("Has elegido las reglas Colombianas");
                reglas = new Reglas_Colombiano();
                break;
            case 6:
                System.out.println("Has elegido las reglas Ponceñas");
                reglas = new Reglas_Ponce();
                break;
            case 7:
                System.out.println("Has elegido las reglas Venezolanas");
                reglas = new Reglas_Venezolano();
                break;
        }
    }

    public void colocarFicha(Jugador jugador){
        Scanner sc = new Scanner(System.in);
        List<Ficha> mano = jugador.getMano();

        if(mano.isEmpty()){
            System.out.println("No tienes fichas para jugar");
            return;
        }

        jugador.mostrarMano();

        System.out.println(jugador.getNombre() + ", elige la ficha que quieres colocar: (0-" + (mano.size() - 1) + ")");
        jugador.getMano();
        int posicionFicha = sc.nextInt();

        if (posicionFicha > mano.size() || posicionFicha < 0){
            System.out.println("El valor introducido no es valido, introduce otro");
        }
        Ficha fichaSeleccionada = mano.get(posicionFicha);
        sc.nextLine();

        System.out.println("Quieres girar la ficha: (S/N)");
        String girar = sc.nextLine().toUpperCase();

        if (girar.equals("S")){
            fichaSeleccionada.girarFicha();
            System.out.println("Ficha girada, ahora es " + fichaSeleccionada);
        }

        System.out.println("Elige la posicion en la que poner la ficha: (0-9)");

        System.out.print("Fila: ");
        int posicionFila = sc.nextInt();

        System.out.print("Columna: ");
        int posicionColumna = sc.nextInt();

        if (tablero[posicionFila][posicionColumna] != null){
            System.out.println("La posicion ya esta ocupada, elige otra");
        }else{
            tablero[posicionFila][posicionColumna] = fichaSeleccionada;
            jugador.getMano().remove(posicionFicha);
        }
    }
}
