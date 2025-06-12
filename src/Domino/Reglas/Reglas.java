package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.io.Serializable;
import java.util.List;

public abstract class Reglas implements Serializable {

    protected boolean haHabidoTranca = false;

    protected boolean modoEnParejas = false;

    public void setModoEnParejas(boolean modo){
        this.modoEnParejas = modo;
    }

    public boolean modoDeJuegoEnParejas() {
        return modoEnParejas;
    }

    public boolean soloEnParejas(){
        return false;
    }

    public abstract boolean rondaGanada(Jugador jugador);

    public abstract int puntuacionFinalPartida();

    public abstract int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas);

    public abstract int jugadorQueEmpiezaLaRonda(List<Jugador> jugador);

    public void informacion(){
        System.out.println("Informacion del reglamento:");
    }

    public boolean hayTranca() {
        return haHabidoTranca;
    }

    public void setTranca(boolean valor) {
        this.haHabidoTranca = valor;
    }

    public void resolverTranca(List<Jugador> jugadores, List<Parejas> parejas, boolean modoParejas) {
        if (!hayTranca()){
            return;
        }

        if (modoParejas){
            Parejas ganador = null;
            int menorSuma = 9999;

            for (int i = 0; i < parejas.size(); i++){
                Parejas pareja = parejas.get(i);
                int sumaPareja = 0;
                List<Jugador> jugadoresPareja = pareja.getJugadoresPareja();

                for (int j = 0; j < jugadoresPareja.size(); j++){
                    List<Ficha> mano = jugadoresPareja.get(j).getMano();

                    for (int k = 0; k < mano.size(); k++){
                        Ficha ficha = mano.get(k);
                        sumaPareja += ficha.getLadoA() + ficha.getLadoB();
                    }
                }
                if (sumaPareja < menorSuma) {
                    menorSuma = sumaPareja;
                    ganador = pareja;
                }
            }

            if (ganador != null) {
                int puntosOtrosJugadores = 0;

                for (int i = 0; i < parejas.size(); i++){
                    if (parejas.get(i) != ganador) {
                        List<Jugador> jugadoresPareja = parejas.get(i).getJugadoresPareja();

                        for (int j = 0; j < jugadoresPareja.size(); j++){
                            List<Ficha> mano = jugadoresPareja.get(j).getMano();

                            for (int k = 0; k < mano.size(); k++) {
                                Ficha ficha = mano.get(k);
                                puntosOtrosJugadores += ficha.getLadoA() + ficha.getLadoB();
                            }
                        }
                    }
                }

                ganador.setPuntuacion(puntosOtrosJugadores);
            }

        } else {
            Jugador ganador = null;
            int menorSuma = 9999;

            for (int i = 0; i < jugadores.size(); i++) {
                Jugador jugador = jugadores.get(i);
                int sumaJugador = 0;
                List<Ficha> mano = jugador.getMano();

                for (int j = 0; j < mano.size(); j++) {
                    Ficha ficha = mano.get(j);
                    sumaJugador += ficha.getLadoA() + ficha.getLadoB();
                }

                if (sumaJugador < menorSuma) {
                    menorSuma = sumaJugador;
                    ganador = jugador;
                }
            }

            if (ganador != null) {
                int puntosOtrosJugadores = 0;

                for (int i = 0; i < jugadores.size(); i++) {
                    if (jugadores.get(i) != ganador) {
                        List<Ficha> mano = jugadores.get(i).getMano();

                        for (int j = 0; j < mano.size(); j++) {
                            Ficha ficha = mano.get(j);
                            puntosOtrosJugadores += ficha.getLadoA() + ficha.getLadoB();
                        }
                    }
                }

                ganador.setPuntuacion(puntosOtrosJugadores);
            }
        }
    }
}
