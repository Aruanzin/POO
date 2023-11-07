package Modelo;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import interfaces.IterageComHeroi;

public class Moeda extends PersonagemMovel implements IterageComHeroi {
	private static final long serialVersionUID = 5725735988597693215L;
	private boolean podeMover = true;

	public Moeda(int lado) {
		super(0, 11);
		this.ladoVirado = lado;
		System.out.println("MOEDA CIRADA");
	}

	public void mover() {
		if (podeMover) {
			switch (ladoVirado) {
			case Consts.BAIXO:
				if (!this.moveDown() || !this.validaPosicao())
					podeMover = false;
				break;
			case Consts.CIMA:
				if (!this.moveUp() || !this.validaPosicao())
					podeMover = false;
				break;
			case Consts.ESQUERDA:
				if (!this.moveLeft() || !this.validaPosicao())
					podeMover = false;
				break;
			case Consts.DIREITA:
				if (!this.moveRight() || !this.validaPosicao())
					podeMover = false;
				break;
			}
		}
	}

	public boolean interageHeroi(Hero hero, ArrayList<Personagem> umaFase) {

		return true;
	}
}
