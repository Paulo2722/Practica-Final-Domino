package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;

public class Reglas_Chileno extends Reglas{

    //!!!!!Me falta lo de la traca
    @Override
    public boolean rondaGanada(Jugador jugador){
        return jugador.getMano().isEmpty();
    }

    @Override
    public int puntuacionFinalPartida() {
        return 121;
    }

    @Override
    public boolean tieneTraca() {
        return true;
    }

    //Provisional
    public int puntuacionTraca(){
        return 1;
    }

    @Override
    public int jugadorQueEmpiezaLaRonda(List<Jugador> jugador) {
        int jugadorQueEmpieza = 0;
        int fichaMasGrande = 0;

        for (int i = 0; i < jugador.size(); i++){
            List<Ficha> mano = jugador.get(i).getMano();

            for (int j = 0; j < mano.size(); j++){
                Ficha ficha = mano.get(j);

                if (ficha.getLadoA() == ficha.getLadoB()){
                    int fichaDoble = ficha.getLadoA();

                    if (fichaDoble > fichaMasGrande){
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
        int puntuacionMasBaja = 131;
        boolean finalPartida = false;

        for (int i = 0; i < jugadores.size(); i++){
            Jugador jugador = jugadores.get(i);
            List<Ficha> mano = jugador.getMano();
            int puntuacion = 0;

            for (int j = 0; j < mano.size(); j++){
                Ficha ficha = mano.get(j);
                puntuacion += ficha.getLadoA() + ficha.getLadoB();
            }
            jugador.setPuntuacion(jugador.getPuntuacion() + puntuacion);

            if (jugador.getPuntuacion() >= puntuacionFinalPartida()){
                finalPartida = true;
            }
        }

        if (finalPartida){
            Jugador jugadorGanador = jugadores.get(0);

            for (int i = 0; i < jugadores.size(); i++){
                if (jugadores.get(i).getPuntuacion() > jugadorGanador.getPuntuacion()){
                    jugadorGanador = jugadores.get(i);
                }
            }
            System.out.println("El jugador " + jugadorGanador.getNombre() + " ha ganado la partida");
        }

        return 0;
    }

    @Override
    public boolean modoDeJuegoEnParejas(){
        return false;
    }
}
