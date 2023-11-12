package Modelo;

import interfaces.IterageComHeroi;
import Auxiliar.Consts;
import Controler.Fase;

public class ObstaculoMovel extends Personagem implements IterageComHeroi {
	private static final long serialVersionUID = 1L;

	public ObstaculoMovel() {
		super(0, 8);
		super.bTransponivel = false;
	}
	public ObstaculoMovel(int x, int y) {
		super(x, y);
		super.bTransponivel = false;
	}

	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		Personagem p = null;
		switch (hero.getLado()) {
		case Consts.BAIXO:
			p = moveDown();
			break;
		case Consts.CIMA:
			p = moveUp();
			break;
		case Consts.ESQUERDA:
			p = moveLeft();
			break;
		case Consts.DIREITA:
			p = moveRight();
			break;
		}
		return p;
	}
}
