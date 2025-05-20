package Domino.Reglas;

import Domino.Juego.Jugador;
import Domino.Juego.Parejas;
import Domino.Juego.Partida;

import java.util.List;

public abstract class Reglas {

    public abstract boolean rondaGanada(Jugador jugador);

    public abstract int puntuacionFinalPartida();

    public abstract int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas);

    public abstract int jugadorQueEmpiezaLaRonda(List<Jugador> jugador);

    public abstract boolean tieneTraca();

    public abstract boolean modoDeJuegoEnParejas();
}
