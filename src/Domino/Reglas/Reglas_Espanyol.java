package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.io.Serializable;
import java.util.List;

public class Reglas_Espanyol extends Reglas implements Serializable {

    @Override
    public boolean rondaGanada(Jugador jugador) {
        return jugador.getMano().isEmpty();
    }

    @Override
    public int puntuacionFinalPartida() {
        return 200;
    }

    @Override
    public boolean modoDeJuegoEnParejas() {
        return super.modoDeJuegoEnParejas();
    }

    @Override
    public int jugadorQueEmpiezaLaRonda(List<Jugador> jugador) {
        int jugadorQueEmpieza = 0;
        int fichaMasGrande = 0;

        for (int i = 0; i < jugador.size(); i++) {
            List<Ficha> mano = jugador.get(i).getMano();

            for (int j = 0; j < mano.size(); j++) {
                Ficha ficha = mano.get(j);

                if (ficha.getLadoA() == ficha.getLadoB()) {
                    int fichaDoble = ficha.getLadoA();

                    if (fichaDoble > fichaMasGrande) {
                        fichaMasGrande = fichaDoble;
                        jugadorQueEmpieza = i;
                    }
                }
            }
        }
        return jugadorQueEmpieza;
    }

    @Override
    public int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas) {
        if (modoDeJuegoEnParejas()) {
            for (int i = 0; i < parejas.size(); i++) {
                Parejas pareja = parejas.get(i);
                List<Jugador> jugadoresPareja = pareja.getJugadoresPareja();
                Jugador jugadorGanador = null;
                int puntuacionPareja = 0;

                for (int j = 0; j < jugadoresPareja.size(); j++) {
                    Jugador jugador = jugadoresPareja.get(j);
                    List<Ficha> mano = jugador.getMano();

                    if (mano.isEmpty()) {
                        jugadorGanador = jugador;

                    } else {
                        for (int n = 0; n < mano.size(); n++) {
                            Ficha ficha = mano.get(n);
                            puntuacionPareja += ficha.getLadoA() + ficha.getLadoB();
                        }
                    }
                }
                pareja.setPuntuacion(puntuacionPareja);

                if (jugadorGanador != null && jugadorGanador.getPuntuacion() >= puntuacionFinalPartida()) {
                    System.out.println("La pareja " + pareja.getNombre() + " ha ganado la partida");
                }
            }
        } else {
            Jugador jugadorGanador = null;
            int puntosTotalesRonda = 0;

            for (int i = 0; i < jugadores.size(); i++) {
                if (jugadores.get(i).getMano().isEmpty()) {
                    jugadorGanador = jugadores.get(i);
                    break;
                }
            }
            if (jugadorGanador != null) {
                for (int i = 0; i < jugadores.size(); i++) {
                    Jugador jugador = jugadores.get(i);

                    if (jugador != jugadorGanador) {
                        List<Ficha> mano = jugador.getMano();

                        for (int j = 0; j < mano.size(); j++) {
                            Ficha ficha = mano.get(j);
                            puntosTotalesRonda += ficha.getLadoA() + ficha.getLadoB();
                        }
                    }
                }
                jugadorGanador.setPuntuacion(jugadorGanador.getPuntuacion() + puntosTotalesRonda);

                if (jugadorGanador.getPuntuacion() >= puntuacionFinalPartida()) {
                    System.out.println("El jugador " + jugadorGanador.getNombre() + " ha ganado la partida");
                }
            }
            if (jugadorGanador != null) {
                return jugadorGanador.getPuntuacion();
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public void informacion(){
        System.out.println("1.Objetivo: Llegar a 100 puntos");
        System.out.println("2.Modalidades: Individual o en parejas");
        System.out.println("3.Salida: Empieza el que tiene el 6 doble");
        System.out.println("4.Finalizacion: Gana el jugador que tenga haya alcanzado los 100 puntos");
        System.out.println("5.Suma: Al finalizar una ronda, cada jugador sumara a su puntuacion su mano");
    }
}
