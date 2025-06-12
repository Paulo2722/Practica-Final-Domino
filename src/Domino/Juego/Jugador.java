package Domino.Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Serializable {
    private String nombre;
    private Parejas pareja;
    private List<Ficha> mano;
    private int puntuacion = 0;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public List<Ficha> getMano(){
        return mano;
    }

    public int getPuntuacion(){
        return puntuacion;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Parejas getPareja(){
        return pareja;
    }

    public void setPareja(Parejas pareja){
        this.pareja = pareja;
    }

    public void setPuntuacion(int puntuacion){
        this.puntuacion = puntuacion;
    }

    public void recibirFicha(Ficha ficha){
        mano.add(ficha);
    }

    public void mostrarMano(){
        System.out.println(nombre + ", tu mano: " + mano);
    }
}
