package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;
import Domino.Juego.Parejas;

import java.util.List;
import java.util.Scanner;

public class Reglas_Mexicano extends Reglas{

    //!!!!!Me falta lo de la traca
    @Override
    public boolean rondaGanada(Jugador jugador){
        return jugador.getMano().isEmpty();
    }

    @Override
    public int puntuacionFinalPartida() {
        return 200;
    }

    @Override
    public boolean modoDeJuegoEnParejas(){
        Scanner sc = new Scanner(System.in);
        int respuesta;

        while (true) {
            System.out.println("Elige el modo de juego:");
            System.out.println("Pulsa 1 si quieres el modo individual, pulsa 2 si quieres el modo en parejas");
            try {
                respuesta = sc.nextInt();
                if (respuesta == 1) {
                    return false;
                }else if (respuesta == 2){
                    return true;
                }
            } catch (Exception e) {
                System.out.print("El valor introducido no es valido, introduce un nuevo valor");
            }
        }
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

                if (ficha.getLadoA() + ficha.getLadoB() > fichaMasGrande){
                    fichaMasGrande = ficha.getLadoA() + ficha.getLadoB();
                    jugadorQueEmpieza = i;
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
                pareja.setPuntuacion(pareja.getPuntuacionEquipo() + puntuacionPareja);

                if (jugadorGanador != null && jugadorGanador.getPuntuacion() >= puntuacionFinalPartida()) {
                    System.out.println("La pareja " + pareja.getNombre() + " ha ganado la partida");
                }
            }
        } else {

            for (int i = 0; i < jugadores.size(); i++) {
                List<Ficha> mano = jugadores.get(i).getMano();
                Jugador jugador = jugadores.get(i);
                Jugador jugadorGanador = null;
                int puntuacion = 0;

                if (mano.isEmpty()) {
                    jugadorGanador = jugadores.get(i);

                } else {

                    for (int j = 0; j < mano.size(); j++) {
                        Ficha ficha = mano.get(j);
                        puntuacion += ficha.getLadoA() + ficha.getLadoB();
                    }
                }
                jugador.setPuntuacion(jugador.getPuntuacion() + puntuacion);

                if (jugadorGanador != null && puntuacion >= puntuacionFinalPartida()) {
                    System.out.println("El jugador" + jugador.getNombre() + "ha ganado la partida");
                }
                return puntuacion;
            }
        }
        return 0;
    }
}
