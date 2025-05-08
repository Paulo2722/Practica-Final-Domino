package Domino.Reglas;

import Domino.Juego.Jugador;

import java.util.List;

public abstract class Reglas {

    public abstract boolean rondaGanada(Jugador jugador);

    public abstract int puntuacionFinalPartida();

    public abstract int puntuacionJugador(List<Jugador> jugadores);

    public abstract int jugadorQueEmpiezaLaRonda(List<Jugador> jugador);

    public abstract boolean tieneTraca();
}
