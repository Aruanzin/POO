package Modelo;

import Controler.Fase;
import interfaces.IterageComHeroi;

public class Vida extends Personagem implements IterageComHeroi{
	private static final long serialVersionUID = 1L;

	public Vida() {
		super(5,4);
		this.bTransponivel = false;
	}
	
	public boolean interageHeroi(Hero hero, Fase umaFase) {
    	hero.setNumeroVidasRestantes(1);
	    umaFase.removePersonagem(this);
	    umaFase.setNumeroVidas(umaFase.getNumeroVidas()-1);
	    return true;
	}
}
