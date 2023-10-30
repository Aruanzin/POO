package Modelo;

import java.util.ArrayList;

import interfaces.IterageComHeroi;

public class Vida extends Personagem implements IterageComHeroi{
	private static final long serialVersionUID = 1L;

	public Vida() {
		super(5,4);
	}
	public boolean interageHeroi(Hero hero,ArrayList<Personagem> umaFase) {
    	hero.setNumeroVidasRestantes(1);
	    umaFase.remove(this);
	    return true;
	}
}
