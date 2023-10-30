package Modelo;

import java.util.ArrayList;

import interfaces.IterageComHeroi;

public class Bau extends Personagem implements IterageComHeroi{

    private static final long serialVersionUID = 1L;

	public Bau() {
        super(5,5);
    }

	public boolean interageHeroi(Hero hero,ArrayList<Personagem> umaFase) {
		hero.setPassouFase(true);
		return true;
	}
}
