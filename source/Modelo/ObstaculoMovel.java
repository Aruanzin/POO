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

	public boolean interageHeroi(Hero hero, Fase umaFase) {
		boolean posicaoValida = true;
		switch (hero.getLado()) {
		case Consts.BAIXO:
			if (!moveDown())
				posicaoValida = false;
			break;
		case Consts.CIMA:
			if (!moveUp())
				posicaoValida = false;
			break;
		case Consts.ESQUERDA:
			if (!moveLeft())
				posicaoValida = false;
			break;
		case Consts.DIREITA:
			if (!moveRight())
				posicaoValida = false;
			break;
		}
		return posicaoValida;
	}
}
