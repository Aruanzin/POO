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

	public boolean interageHeroi(Hero hero, Fase umaFase) {
		boolean posicaoValida = true;
		switch (hero.getLado()) {
		case Consts.BAIXO:
			if (moveDown() == null)
				posicaoValida = false;
			break;
		case Consts.CIMA:
			if (moveUp() == null)
				posicaoValida = false;
			break;
		case Consts.ESQUERDA:
			if (moveLeft()== null)
				posicaoValida = false;
			break;
		case Consts.DIREITA:
			if (moveRight()== null)
				posicaoValida = false;
			break;
		}
		System.out.println("POSICAO VALIDA" + posicaoValida);
		return posicaoValida;
	}
}
