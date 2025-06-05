package Domino.Reglas;

import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;
import java.util.Scanner;

public abstract class Reglas {

    protected boolean haHabidoTranca = false;

    public abstract boolean rondaGanada(Jugador jugador);

    public abstract int puntuacionFinalPartida();

    public abstract int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas);

    public abstract int jugadorQueEmpiezaLaRonda(List<Jugador> jugador);

    public boolean modoDeJuegoEnParejas() {
        Scanner sc = new Scanner(System.in);
        String respuesta;

        while (true) {
            System.out.println("Elige el modo de juego:");
            System.out.println("Pulsa 'I' si quieres el modo individual, pulsa 'P' si quieres el modo en parejas");

            respuesta = sc.nextLine().toLowerCase();

            if (respuesta.equals("i")){
                return false;
            }else if (respuesta.equals("p")){
                return true;
            }else{
                System.out.println("El valor introducido no es valido");
            }
        }
    }

    public boolean hayTranca() {
        return haHabidoTranca;
    }

    public void setTranca(boolean valor) {
        this.haHabidoTranca = valor;
    }
}
