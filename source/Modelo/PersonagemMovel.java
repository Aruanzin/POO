package Modelo;

import Auxiliar.Desenho;
import Auxiliar.Consts;

public abstract class PersonagemMovel extends Personagem {
	
	private static final long serialVersionUID = -6656618292131139401L;
	protected int ladoVirado;

	public PersonagemMovel(int X, int Y) {
		super(X, Y);
	}
	public void mover() {
		switch (ladoVirado) {
		case Consts.BAIXO:
			if (!this.moveDown() || !this.validaPosicao())
				Desenho.acessoATelaDoJogo().removePersonagem(this);
			break;
		case Consts.CIMA:
			if (!this.moveUp() || !this.validaPosicao())
				Desenho.acessoATelaDoJogo().removePersonagem(this);
			break;
		case Consts.ESQUERDA:
			if (!this.moveLeft() || !this.validaPosicao())
				Desenho.acessoATelaDoJogo().removePersonagem(this);
			break;
		case Consts.DIREITA:
			if (!this.moveRight() || !this.validaPosicao())
				Desenho.acessoATelaDoJogo().removePersonagem(this);
			break;
		}
	}
	public void autoDesenho() {
    	super.autoDesenho();
    	this.mover();
    }
}
