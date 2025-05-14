package Domino.Reglas;

import Domino.Juego.Ficha;
import Domino.Juego.Jugador;

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

    public int puntuacionTraca(){
        if (tieneTraca()){

        }
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
