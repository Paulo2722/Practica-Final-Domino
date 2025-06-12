package Domino.Guardado;

import Domino.Juego.Tablero;
import java.io.*;

public class InputOutput {
    private static final String archivo_guardado = "partida_guardada.ser";

    public static void guardarPartida(Tablero tablero, String nombreArchivo) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            salida.writeObject(tablero);
            System.out.println("Partida guardada correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    public static Tablero cargarPartida(String nombreArchivo) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            Tablero tablero = (Tablero) entrada.readObject();
            System.out.println("Partida cargada correctamente desde " + nombreArchivo);
            return tablero;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }
}
