package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;

public class Reglas_Latin extends Reglas{

    @Override
    public boolean rondaGanada(Jugador jugador){
        return false;
    }

    @Override
    public int puntuacionFinalPartida(){
        return 100;
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
        String nombre;

        for (int i = 0; i < jugador.size(); i++) {
            List<Ficha> mano = jugador.get(i).getMano();
            nombre = jugador.get(i).getNombre();

            for (int j = 0; j < mano.size(); j++) {
                Ficha ficha = mano.get(j);

                if (ficha.getLadoA() == 6 && ficha.getLadoB() == 6) {
                    jugadorQueEmpieza = i;
                    System.out.println("El jugador que empieza es " + nombre);
                }
            }
        }
        return jugadorQueEmpieza;
    }

    @Override
    public boolean hayTranca(){
        return false;
    }

    @Override
    public boolean modoDeJuegoEnParejas(){
        return super.modoDeJuegoEnParejas();
    }
}
