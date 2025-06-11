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
        this.nombre = nombre;
    }

    public Parejas getPareja(){
        return pareja;
    }

    public List<Jugador> getJugadoresPareja(){
        return jugadoresPareja;
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
        this.puntuacionEquipo += puntuacion;
    }

    public void agregarJugador(Jugador jugador){
        jugadoresPareja.add(jugador);
        jugador.setPareja(this);
    }
}
