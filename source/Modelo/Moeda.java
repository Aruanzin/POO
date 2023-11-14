package Modelo;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Fase;

public class Moeda extends ObstaculoMovel {
	private static final long serialVersionUID = 5725735988597693215L;
	private boolean estaNoRio = false;
	private int timer = 1;
	private boolean estaComHeroi = false;
	private int vezesChamada = 0;
	private boolean jaEsteveNoRio = false;
	private boolean jaEntrouHeroi = false;
	private Personagem monstroAnterior; 

	public Moeda(Personagem monstro) {
		super(0, 11);
		monstroAnterior = monstro;
		this.bTransponivel = false;
	}

	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		Personagem p = super.interageHeroi(hero, umaFase);
		if (p instanceof Rio) {
			setPosicao(p.pPosicao);
			estaNoRio = true;
			jaEsteveNoRio = true;
			setImage(2, 11);
			System.out.println("RIO");
			this.bTransponivel = true;
		}
		if (hero.pPosicao.igual(pPosicao)) {
			if (!estaComHeroi) {
				hero.setPosicao(pPosicao);
				estaComHeroi = true;
			} else {
				estaComHeroi = false;
				jaEntrouHeroi = true;
				switch (hero.getLado()) {

				case Consts.BAIXO:
					hero.moveDown(Consts.CELL_SIDE);
					break;
				case Consts.CIMA:
					hero.moveUp(Consts.CELL_SIDE);
					break;
				case Consts.ESQUERDA:
					hero.moveLeft(Consts.CELL_SIDE);
					break;
				case Consts.DIREITA:
					hero.moveRight(Consts.CELL_SIDE);
					break;

				}
			}
		}
		System.out.println("moeda");
		return estaNoRio ? null : this;
	}

	public void autoDesenho() {
		super.autoDesenho();
		timer++;
		if (estaNoRio && jaEntrouHeroi && timer % 5 == 0) {
			if (this.pPosicao.getColuna() < Consts.RES * Consts.CELL_SIDE / 2) {
				if (moveRight() != null && posicaoValida()) {
					System.out.println("MOVEU DIREITA");
					pPosicao.moveRight();
				} else {
					processaTempo();
					timer = 0;
				}
			} else {
				if (moveLeft() != null && posicaoValida()) {
					pPosicao.moveLeft();
				} else {
					processaTempo();
					timer = 0;
				}
			}
		}
		if (jaEsteveNoRio && !estaNoRio) {
			processaTempo();
		}

	}

	private void processaTempo() {
		estaNoRio = false;
		if (timer % 5 == 0) {
			System.out.println(vezesChamada);
			switch (vezesChamada) {
			case 0:
				pPosicao.volta();
				setImage(2, 11);
				break;
			case 1:
				setImage(3, 11);
				break;
			case 2:
				setImage(4, 11);
				break;
			default:
				Desenho.acessoATelaDoJogo().removePersonagem(this);
			}
			vezesChamada++;
		}

		timer++;
	}

	private boolean posicaoValida() {
		boolean valido = true;
		ArrayList<Personagem> personagens = Desenho.acessoATelaDoJogo().getPersonagens();
		for (int i = 1; i< personagens.size() ; i++) {
			Personagem p = personagens.get(i);
			if (p.pPosicao.igual(pPosicao) && !(p instanceof Rio) && !p.equals(this)) {
				valido = false;
			}
		}
		return valido;
	}

}
