package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;
import java.util.Scanner;

public abstract class Reglas {

    protected boolean haHabidoTranca = false;

    public abstract boolean rondaGanada(Jugador jugador);

    public abstract int puntuacionFinalPartida();

    public abstract int puntuacionJugador(List<Jugador> jugadores, List<Parejas> parejas);

    public abstract int jugadorQueEmpiezaLaRonda(List<Jugador> jugador);

    public boolean modoDeJuegoEnParejas() {
        Scanner sc = new Scanner(System.in);
        String respuesta;

        while (true) {
            System.out.println("Elige el modo de juego:");
            System.out.println("Pulsa 'I' si quieres el modo individual, pulsa 'P' si quieres el modo en parejas");

            respuesta = sc.nextLine().toLowerCase();

            if (respuesta.equals("i")){
                return false;
            }else if (respuesta.equals("p")){
                return true;
            }else{
                System.out.println("El valor introducido no es valido");
            }
        }
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
