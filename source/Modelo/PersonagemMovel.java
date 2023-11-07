package Modelo;

import Auxiliar.Consts;

public abstract class PersonagemMovel extends Personagem {
	
	private static final long serialVersionUID = -6656618292131139401L;
	protected int ladoVirado;
	protected boolean podeMoverAposBater = true;

	public PersonagemMovel(int X, int Y) {
		super(X, Y);
	}
	public void mover() {
		if (podeMoverAposBater) {
			switch (ladoVirado) {
			case Consts.BAIXO:
				if (!this.moveDown())
					quandoBater();
				break;
			case Consts.CIMA:
				if (!this.moveUp())
					quandoBater();
				break;
			case Consts.ESQUERDA:
				if (!this.moveLeft())
					quandoBater();
				break;
			case Consts.DIREITA:
				if (!this.moveRight())
					quandoBater();
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
