package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.io.Serializable;
import java.util.List;

public class Reglas_Venezolano extends Reglas implements Serializable {

    @Override
    public boolean rondaGanada(Jugador jugador) {
        return false;
    }

    @Override
    public int puntuacionFinalPartida() {
        return 75;
    }

    @Override
    public int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas) {
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
        return 0;
    }

    @Override
    public int jugadorQueEmpiezaLaRonda(List<Jugador> jugador) {
        int jugadorQueEmpieza = 0;

        for (int i = 0; i < jugador.size(); i++){
            List<Ficha> mano = jugador.get(i).getMano();

            for (int j = 0; j < mano.size(); j++){
                Ficha ficha = mano.get(j);

                if (ficha.getLadoA() == 6 && ficha.getLadoB() == 6){
                    jugadorQueEmpieza = i;
                }
            }
        }
        return jugadorQueEmpieza;
    }

    @Override
    public boolean hayTranca() {
        return false;
    }

    @Override
    public boolean modoDeJuegoEnParejas() {
        return super.modoDeJuegoEnParejas();
    }

    @Override
    public boolean soloEnParejas(){
        return true;
    }

    @Override
    public void informacion(){
        System.out.println("1.Objetivo: Llegar a 75 puntos");
        System.out.println("2.Modalidades: Solo en parejas");
        System.out.println("3.Salida: Empieza el que tiene el 6 doble");
        System.out.println("4.Finalizacion: Gana la pareja que haya alcanzado los 75 puntos");
        System.out.println("5.Suma: Al finalizar una ronda, la pareja ganadora sumara los puntos no jugados de los rivales");
    }
}
