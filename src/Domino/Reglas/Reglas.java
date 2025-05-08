package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;

import java.util.List;

public abstract class Reglas {

    public abstract boolean victoria(Jugador jugador);

    public abstract int puntuacionVictoria();

    public abstract int puntuacionJugador(Jugador jugador, Ficha ficha);

    public abstract int jugadorQueEmpiezaLaRonda(Jugador jugador);

}
