package Modelo;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.Monstro;

public class Moeda extends ObstaculoMovel implements Monstro {
	private static final long serialVersionUID = 5725735988597693215L;
	private boolean estaNoAgua = false;
	private int timer = 1;
	private boolean estaComHeroi = false;
	private int vezesChamada = 0;
	private boolean jaEsteveNoAgua = false;
	private boolean jaEntrouHeroi = false;
	private boolean destruidaPorHeroi = false;
	private Personagem monstroAnteAguar;

	public Moeda(Personagem monstro) {
		super(0, 11);
		monstroAnteAguar = monstro;
		this.bTransponivel = false;
	}

	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		Personagem p = super.interageHeroi(hero, umaFase);

		if (p instanceof Rio && !jaEsteveNoAgua) {
			entraNaAgua(p);
		}
		if (hero.pPosicao.igual(pPosicao) && estaNoAgua) {
			processaQuandoHeroiEstaNaAgua(hero);
		}
		return estaNoAgua ? null : this;
	}
	private void entraNaAgua(Personagem p) {
		setPosicao(p.pPosicao);
		estaNoAgua = true;
		jaEsteveNoAgua = true;
		setImage(2, 11);
		this.bTransponivel = true;

	}
	private void processaQuandoHeroiEstaNaAgua(Hero hero) {
		if (!estaComHeroi) {
			hero.setPosicao(pPosicao);
			estaComHeroi = true;
		} else {
			Personagem p = null;
			switch (hero.getLado()) {

			case Consts.BAIXO:
				p = hero.moveDown(Consts.CELL_SIDE);
				break;
			case Consts.CIMA:
				p = hero.moveUp(Consts.CELL_SIDE);
				break;
			case Consts.ESQUERDA:
				p = hero.moveLeft(Consts.CELL_SIDE);
				break;
			case Consts.DIREITA:
				p = hero.moveRight(Consts.CELL_SIDE);
				break;

			}
			if (p == null || (p != null && p.bTransponivel)) {
				estaComHeroi = false;
				jaEntrouHeroi = true;
			}else {
				hero.setPosicao(pPosicao);
			}
			
		}

	}

	public void autoDesenho() {
		super.autoDesenho();
		timer++;
		if (estaNoAgua && jaEntrouHeroi && timer % 5 == 0) {
			andaSobreAgua();
		}
		if (jaEsteveNoAgua && !estaNoAgua) {
			processaTempo();
		}
		if (!estaNoAgua && !jaEsteveNoAgua) {
			destruirDepoisDoAgua();
		}
		if (destruidaPorHeroi) {
			destruirPorHeroi();
		}

	}
	private void destruirPorHeroi() {
		if (!this.pPosicao.setPosicao(this.pPosicao.getLinha() - Consts.CELL_SIDE, this.pPosicao.getColuna())) {
			Desenho.acessoATelaDoJogo().removePersonagem(this);
		}

	}
	private void destruirDepoisDoAgua() {
		if (timer % 60 == 0) {
			this.setImage(1, 11);
		}
		if (timer == 80) {
			monstroAnteAguar.setPosicao(this.pPosicao.getLinha(), this.pPosicao.getColuna());
			Desenho.acessoATelaDoJogo().removePersonagem(this);
			Desenho.acessoATelaDoJogo().addPersonagem(monstroAnteAguar);
		}

	}
	
	private void andaSobreAgua() {
		if (this.pPosicao.getColuna() < Consts.RES * Consts.CELL_SIDE / 2) {
			if (moveRight() != null && posicaoValida()) {
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

	private void processaTempo() {
		estaNoAgua = false;
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
		for (int i = 1; i < personagens.size(); i++) {
			Personagem p = personagens.get(i);
			if (p.pPosicao.igual(pPosicao) && !(p instanceof Rio) && !p.equals(this)) {
				valido = false;
			}
		}
		return valido;
	}

	@Override
	public void acabouAsVidas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void morreuPorTiro() {
		destruidaPorHeroi = true;
	}

}
