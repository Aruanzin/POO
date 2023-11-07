package Modelo;

import java.util.ArrayList;

import interfaces.IterageComHeroi;

public class Caveira extends Personagem implements IterageComHeroi{
    private static final long serialVersionUID = 20231107083000L;

    public Caveira() {
        super(0,13);
        super.bTransponivel = false;
    }
    public boolean interageHeroi(Hero hero,ArrayList<Personagem> umaFase) {
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.remove(this);
	    return true;
    }
}
