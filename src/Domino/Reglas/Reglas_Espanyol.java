package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;

import java.util.List;

public class Reglas_Espanyol extends Reglas{

    @Override
    public boolean rondaGanada(Jugador jugador){
        return jugador.getMano().isEmpty();
    }

    @Override
    public int puntuacionFinalPartida() {
        return 200;
    }

    @Override
    public boolean tieneTraca() {
        return false;
    }

    @Override
    public boolean soloSeJuegaEnParejas(){
        return false;
    };

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
    public int puntuacionJugador(List<Jugador> jugadores) {
        int puntuacion = 0;
        Jugador jugador = null;

        for (int i = 0; i < jugadores.size(); i++){
            List<Ficha> mano = jugadores.get(i).getMano();

            if (mano.isEmpty()){
                jugador = jugadores.get(i);
            }else{
                for (int j = 0; j < mano.size(); j++){
                    Ficha ficha = mano.get(j);
                    puntuacion += ficha.getLadoA() + ficha.getLadoB();
                }
            }
        }
        if (jugador != null){
            jugador.setPuntuacion(puntuacion);
        }
        if (puntuacion >= puntuacionFinalPartida()){
            System.out.println("El jugador" + jugador.getNombre() + "ha ganado la partida");
        }
        return puntuacion;
    }
}
