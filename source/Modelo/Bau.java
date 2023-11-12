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

	public Personagem interageHeroi(Hero hero,Fase umaFase) {
		if(!estaDesbloqueado) {
			return null;
		}
	    umaFase.removePersonagem(this);
		return this;
	}
	
	public void setDesbloqueado() {
		this.setImage(1, 6);
		estaDesbloqueado = true;
		super.bTransponivel = true;
		
	}
	
	
}
