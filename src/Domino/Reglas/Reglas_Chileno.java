package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;

public class Reglas_Chileno extends Reglas{

    @Override
    public boolean rondaGanada(Jugador jugador){
        return jugador.getMano().isEmpty();
    }

    @Override
    public int puntuacionFinalPartida() {
        return 121;
    }

    @Override
    public boolean hayTranca() {
        return false;
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
        int puntuacionMasBaja = 121;
        boolean finalPartida = false;

        if (modoDeJuegoEnParejas()) {
            Parejas parejaGanadora = null;

            for (int i = 0; i < parejas.size(); i++){
                List<Jugador> miembros = parejas.get(i).getJugadoresPareja();

                for (int j = 0; j < miembros.size(); j++){
                    if (miembros.get(j).getMano().isEmpty()){
                        parejaGanadora = parejas.get(i);
                    }
                }
            }

            for (int i = 0; i < parejas.size(); i++) {
                Parejas pareja = parejas.get(i);
                if (pareja != parejaGanadora) {
                    int puntuacion = 0;
                    List<Jugador> miembros = pareja.getJugadoresPareja();

                    for (int j = 0; j < miembros.size(); j++) {
                        List<Ficha> mano = miembros.get(i).getMano();

                        for (int n = 0; n < mano.size(); n++) {
                            Ficha ficha = mano.get(n);
                            puntuacion += ficha.getLadoA() + ficha.getLadoB();
                        }
                    }
                    pareja.setPuntuacion(puntuacion);

                    if (pareja.getPuntuacionEquipo() >= puntuacionFinalPartida()) {
                        finalPartida = true;
                    }
                }
            }
            if (finalPartida){
                Parejas ganadora = null;

                for (int i = 0; i < parejas.size(); i++){
                    int puntuacion = parejas.get(i).getPuntuacionEquipo();

                    if (puntuacion < puntuacionMasBaja){
                        puntuacionMasBaja = puntuacion;
                        ganadora = parejas.get(i);
                    }
                }
                if (ganadora != null){
                    System.out.println("La pareja " + ganadora.getNombre() + "ha ganado la partida");
                }
            }
        } else {
            Jugador jugadorSinFichas = null;

            for (int i = 0; i < jugadores.size(); i++){
                if (jugadores.get(i).getMano().isEmpty()){
                    jugadorSinFichas = jugadores.get(i);
                    break;
                }
            }

            for (int i = 0; i < jugadores.size(); i++){
                Jugador jugador = jugadores.get(i);

                if (jugador != jugadorSinFichas){
                    List<Ficha> mano = jugador.getMano();
                    int puntuacion = 0;

                    for (int j = 0; j < mano.size(); j++) {
                        Ficha ficha = mano.get(j);
                        puntuacion += ficha.getLadoA() + ficha.getLadoB();
                    }
                    jugador.setPuntuacion(puntuacion);

                    if (jugador.getPuntuacion() >= puntuacionFinalPartida()) {
                        finalPartida = true;
                    }
                }
            }
            if (finalPartida) {
                Jugador jugadorGanador = null;

                for (int i = 0; i < jugadores.size(); i++) {
                    if (jugadores.get(i).getPuntuacion() < puntuacionMasBaja) {
                        puntuacionMasBaja = jugadores.get(i).getPuntuacion();
                        jugadorGanador = jugadores.get(i);
                    }
                }
                if (jugadorGanador != null) {
                    System.out.println("El jugador " + jugadorGanador.getNombre() + " ha ganado la partida");
                }
            }
        }
        return 0;
    }

    @Override
    public boolean modoDeJuegoEnParejas(){
        return super.modoDeJuegoEnParejas();
    }
}
