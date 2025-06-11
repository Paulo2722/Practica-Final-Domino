package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;

public class Reglas_Ponce extends Reglas{
    @Override
    public boolean rondaGanada(Jugador jugador) {
        return false;
    }

    @Override
    public int puntuacionFinalPartida() {
        return 20;
    }

    @Override
    public int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas) {
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
    public boolean modoDeJuegoEnParejas() {
        return true;
    }

    public void sumarPuntuacionPorPase(Jugador jugador){
        Parejas pareja = jugador.getPareja();

        if (pareja != null){
            pareja.setPuntuacion(1);
        }
    }
}
