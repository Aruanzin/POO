package Modelo;

import java.util.ArrayList;

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
		ArrayList<Personagem> p = null;
		for (int i = 0; i < 2; i++)
			if (podeMoverAposBater) {
				switch (ladoVirado) {
				case Consts.BAIXO:
					p = this.moveDown();
					if (this.temAlgumMonstro(p)) {
						acertouMonstro(p);
						quandoBater();
						return;
					} else if (this.algumPersonagemTransponivel(p)) {
						continue;
					}else if (p.size() != 0) {
						quandoBater();
						return;
					}
					break;
				case Consts.CIMA:
					p = this.moveUp();
					if (this.temAlgumMonstro(p)) {
						acertouMonstro(p);
						quandoBater();
						return;
					} else if (this.algumPersonagemTransponivel(p)) {
						continue;
					}else if (p.size() != 0) {
						quandoBater();
						return;
					}
					break;
				case Consts.ESQUERDA:
					p = this.moveLeft();
					if (this.temAlgumMonstro(p)) {
						acertouMonstro(p);
						quandoBater();
						return;
					} else if (this.algumPersonagemTransponivel(p)) {
						continue;
					}else if (p.size() != 0) {
						quandoBater();
						return;
					}
					break;
				case Consts.DIREITA:
					p = this.moveRight();
					if (this.temAlgumMonstro(p)) {
						acertouMonstro(p);
						quandoBater();
						return;
					} else if (this.algumPersonagemTransponivel(p)) {
						continue;
					}else if (p.size() != 0) {
						quandoBater();
						return;
					}
					break;
				}
				System.out.println(p.toString());
			}
	}

	private void acertouMonstro(ArrayList<Personagem> personagens) {
		for (Personagem pIesimoPersonagem : personagens) {
			if (pIesimoPersonagem instanceof Monstro) {
				((Monstro) pIesimoPersonagem).morreuPorTiro(); // Se encontrar um personagem transponível, retorna true
			}
		}
	}

	public abstract void quandoBater();

	public void autoDesenho() {
		this.mover();
		super.autoDesenho();
	}
}
