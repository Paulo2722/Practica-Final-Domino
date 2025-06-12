package Domino.Juego;

import Domino.Guardado.InputOutput;
import java.util.Scanner;

public class Partida {
    public void partida() {
        Tablero tablero;
        Scanner sc = new Scanner(System.in);

        System.out.println("¿Quieres cargar una partida guardada? (S/N)");
        String respuesta = sc.nextLine().toUpperCase();

        if (respuesta.equals("S")){
            Tablero partidaCargada = InputOutput.cargarPartida("partida_guardada.ser");
            if (partidaCargada != null){
                tablero = partidaCargada;
            }else{
                System.out.println("No hay partidas guardadas");
                tablero = new Tablero();
            }
        }else{
            tablero = new Tablero();
        }

        tablero.elegirReglas();
        boolean partidaEnParejas = tablero.modoDeJuegoEnParejas();
        tablero.reglas.setModoEnParejas(partidaEnParejas);
        tablero.configurarModoDeJuego(tablero.reglas);

        int numeroDeJugadores;

        if (partidaEnParejas){
            numeroDeJugadores = 4;
            System.out.println("El modo de juego seleccionado es el modo en parejas, habrán 4 jugadores en total");
        }else{
            numeroDeJugadores = 2;
            System.out.println("El modo de juego seleccionado es el modo individual, habrán 2 jugadores en total");
        }
        tablero.crearJugadores(numeroDeJugadores);

        if (partidaEnParejas){
            tablero.crearParejas();
        }

        boolean partidaFinalizada = false;
        //Partida
        do {
            tablero.limpiarTablero();

            tablero.crearFichas();

            tablero.repartirFichas(numeroDeJugadores);
            int turno = tablero.reglas.jugadorQueEmpiezaLaRonda(tablero.getJugadores());
            tablero.setPrimeraFichaColocada(false);

            boolean rondaFinalizada = false;
            //Ronda
            do{
                Jugador jugadorActual = tablero.getJugadores().get(turno);
                System.out.println("Turno de " + jugadorActual.getNombre());

                tablero.colocarFicha(jugadorActual);
                tablero.imprimirTablero();

                if (tablero.reglas.rondaGanada(jugadorActual)){
                    System.out.println("El jugador " + jugadorActual.getNombre() + " ha ganado la ronda");

                    rondaFinalizada = true;
                }else if (tablero.hayTranca()){
                    tablero.reglas.resolverTranca(tablero.getJugadores(), tablero.getParejas(), partidaEnParejas);
                    rondaFinalizada = true;
                }else{
                    tablero.robarFichas(jugadorActual);
                    turno = (turno + 1) % numeroDeJugadores;
                }
            }while(!rondaFinalizada);
            //Puntuaciones
            tablero.reglas.puntuacionJugador(tablero.getJugadores(), tablero.getParejas());

            System.out.println("Puntuaciones después de la ronda:");

            if (partidaEnParejas) {
                for (Parejas pareja : tablero.getParejas()) {
                    System.out.println(pareja.getNombre() + ": " + pareja.getPuntuacionEquipo() + " puntos");
                }
            } else {
                for (Jugador jugador : tablero.getJugadores()) {
                    System.out.println(jugador.getNombre() + ": " + jugador.getPuntuacion() + " puntos");
                }
            }
            //Guardar partida
            System.out.println("¿Deseas guardar la partida? (S/N)");
            String guardarRespuesta = sc.nextLine().toUpperCase();

            if (guardarRespuesta.equals("S")) {
                InputOutput.guardarPartida(tablero, "partida_guardada.ser");
            }

            if (partidaEnParejas){
                for (int i = 0; i < tablero.getParejas().size(); i++){
                if (tablero.getParejas().get(i).getPuntuacionEquipo() >= tablero.reglas.puntuacionFinalPartida()){
                    System.out.println(tablero.getParejas().get(i).getNombre() + "ha ganado la partida");
                    partidaFinalizada = true;
                }
            }
        }else{
                for (int i = 0; i < tablero.getJugadores().size(); i++){
                    if (tablero.getJugadores().get(i).getPuntuacion() >= tablero.reglas.puntuacionFinalPartida()){
                        System.out.println(tablero.getJugadores().get(i).getNombre() + "ha ganado la partida");
                        partidaFinalizada = true;
                    }
                }
            }
        }while (!partidaFinalizada);
    }
}
