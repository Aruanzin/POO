package Modelo;

import Controler.Fase;
import interfaces.IterageComHeroi;

public class Bau extends Personagem implements IterageComHeroi{

    private static final long serialVersionUID = 1L;
    private boolean estaDesbloqueado;
	public Bau() {
        super(5,5);
		super.bTransponivel = false;
    }

	public boolean interageHeroi(Hero hero,Fase umaFase) {
		if(!estaDesbloqueado) {
			return false;
		}
	    umaFase.removePersonagem(this);
		return true;
	}
	
	public void setDesbloqueado() {
		this.setImage(1, 6);
		estaDesbloqueado = true;
		super.bTransponivel = true;
	}
}
