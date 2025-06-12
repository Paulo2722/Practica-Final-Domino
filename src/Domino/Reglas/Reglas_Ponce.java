package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.io.Serializable;
import java.util.List;

public class Reglas_Ponce extends Reglas implements Serializable {
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
        return super.modoDeJuegoEnParejas();
    }

    public void sumarPuntuacionPorPase(Jugador jugador) {
        Parejas pareja = jugador.getPareja();

        if (pareja != null) {
            int puntuacionActual = pareja.getPuntuacionEquipo();
            pareja.setPuntuacion(puntuacionActual + 1);
        }
    }

    @Override
    public boolean soloEnParejas(){
        return true;
    }

    @Override
    public void informacion(){
        System.out.println("1.Objetivo: Llegar a 20 puntos");
        System.out.println("2.Modalidades: Solo en parejas");
        System.out.println("3.Salida: Empieza el que tiene el 6 doble");
        System.out.println("4.Finalizacion: Gana la pareja que haya alcanzado los 20 puntos");
        System.out.println("5.Suma: Cada vez que un jugador haga pasar a otro, se le sumara un punto");
    }
}
