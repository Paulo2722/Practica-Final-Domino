package Domino.Reglas;

import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;

public abstract class Reglas {

    protected boolean haHabidoTranca = false;

    public abstract boolean rondaGanada(Jugador jugador);

    public abstract int puntuacionFinalPartida();

    public abstract int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas);

    public abstract int jugadorQueEmpiezaLaRonda(List<Jugador> jugador);

    public abstract boolean modoDeJuegoEnParejas();

    public boolean hayTranca() {
        return haHabidoTranca;
    }

    public void setTranca(boolean valor) {
        this.haHabidoTranca = valor;
    }
}
