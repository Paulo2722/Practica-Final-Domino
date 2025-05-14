package Domino.Juego;

import java.util.ArrayList;
import java.util.List;

public class Parejas {
    private List<Jugador> jugadoresPareja;
    private Parejas pareja;
    private String nombre;
    private int puntuacionEquipo = 0;

    public Parejas(String nombre){
        this.jugadoresPareja = new ArrayList<>();
    }

    public Parejas getPareja(){
        return pareja;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPuntuacionEquipo(){
        return puntuacionEquipo;
    }

    public void setPareja (Parejas pareja){
        this.pareja = pareja;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPuntuacion(int puntuacion){
        this.puntuacionEquipo = puntuacion;
    }
}
