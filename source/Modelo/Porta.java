package Modelo;

import Controler.Fase;
import interfaces.IterageComHeroi;
public class Porta extends Personagem implements IterageComHeroi  {

	private static final long serialVersionUID = -5342484123120788701L;

	public Porta() {
		super(2,7, 16,8);
		this.bTransponivel = false;
	}
	
	public void  abrirPorta() {
		setImage(0,7);
		this.bTransponivel = true;
	}

	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		// TODO Auto-generated method stub
		if(this.bTransponivel) {
			return null;
		}
		return this;
	}
	

}
