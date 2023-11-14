package Modelo;

import Auxiliar.Consts;
import interfaces.Monstro;

public abstract class PersonagemMovel extends Personagem {

	private static final long serialVersionUID = -6656618292131139401L;
	protected int ladoVirado;
	protected boolean podeMoverAposBater = true;

	public PersonagemMovel(int X, int Y) {
		super(X, Y);
	}

	public void mover() {
		Personagem p;
		if (podeMoverAposBater) {
			switch (ladoVirado) {
			case Consts.BAIXO:
				p = this.moveDown();
				if (p != null) {
					if (p instanceof Monstro)
						((Monstro) p).morreuPorTiro();
					quandoBater();
				}
				break;
			case Consts.CIMA:
				p = this.moveUp();
				if (p != null) {
					if (p instanceof Monstro)
						((Monstro) p).morreuPorTiro();
					quandoBater();
				}
				break;
			case Consts.ESQUERDA:
				p = this.moveLeft();
				if (p != null) {
					if (p instanceof Monstro)
						((Monstro) p).morreuPorTiro();
					quandoBater();
				}
				break;
			case Consts.DIREITA:
				p = this.moveRight();
				if (p != null) {
					if (p instanceof Monstro)
						((Monstro) p).morreuPorTiro();
					quandoBater();
				}
				break;
			}
		}

	}

	public abstract void quandoBater();

	public void autoDesenho() {
		super.autoDesenho();
		this.mover();
	}
}
