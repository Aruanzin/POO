package Modelo;

import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Caveira extends Personagem implements IterageComHeroi, Monstro {

	private static final long serialVersionUID = 6516657816035787688L;

	public Caveira() {
		super(0, 13);
		this.bTransponivel = false;
	}

	public boolean interageHeroi(Hero hero, Fase umaFase) {
		return true;
	}

}
