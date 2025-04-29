package Domino.Juego;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Ficha> mano;

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

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void recibirFicha(Ficha ficha){
        mano.add(ficha);
    }

    public void mostrarMano(){
        System.out.println(nombre + ", tu mano: " + mano);
    }
}
