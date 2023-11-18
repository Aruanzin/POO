package Modelo;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Posicao;
import Controler.Fase;
import interfaces.Monstro;

public class Moeda extends ObstaculoMovel implements Monstro {
	private static final long serialVersionUID = 5725735988597693215L;
	private boolean estaNaAgua = false;
	private int timer = 1;
	private boolean estaComHeroi = false;
	private int vezesChamada = 0;
	private boolean jaEsteveNoAgua = false;
	private boolean jaEntrouHeroi = false;
	private boolean destruidaPorHeroi = false;
	private boolean andaVerticalmente = false;
	private Personagem monstroAnteAguar;

	public Moeda(Personagem monstro) {
		super(0, 11);
		monstroAnteAguar = monstro;
		this.bTransponivel = false;
	}

	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		Personagem p = null;
		if (!estaNaAgua)
			p = super.interageHeroi(hero, umaFase);

		if (p instanceof Rio && !jaEsteveNoAgua) {
			entraNaAgua(p);
		} else if (!estaComHeroi && estaNaAgua) {
			hero.setPosicao(pPosicao);
			estaComHeroi = true;
		} else if (estaComHeroi && estaNaAgua) {
			System.out.println("AQUI ENTRPO");
			processaQuandoHeroiEstaNaAgua(hero);
		}
		return estaNaAgua ? null : this;
	}

	private void entraNaAgua(Personagem p) {
		setPosicao(p.pPosicao);
		estaNaAgua = true;
		jaEsteveNoAgua = true;
		setImage(2, 11);
		this.bTransponivel = true;
		ArrayList<Personagem> p1 = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(pPosicao.getLinha(), pPosicao.getColuna() - Consts.CELL_SIDE));
		ArrayList<Personagem> p2 = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(pPosicao.getLinha(), pPosicao.getColuna() + Consts.CELL_SIDE));

		if (!algumPersonagemEhRio(p1) || !algumPersonagemEhRio(p2)) {
			andaVerticalmente = true;
		}

	}

	private void processaQuandoHeroiEstaNaAgua(Hero hero) {
		if (!estaComHeroi) {
			hero.setPosicao(pPosicao);
			estaComHeroi = true;
		} else {
			ArrayList<Personagem> p = null;
			switch (hero.getLado()) {

			case Consts.BAIXO:
				p = hero.moveDown(Consts.CELL_SIDE - Consts.PIXELS);
				break;
			case Consts.CIMA:
				p = hero.moveUp(Consts.CELL_SIDE- Consts.PIXELS);
				break;
			case Consts.ESQUERDA:
				p = hero.moveLeft(Consts.CELL_SIDE- Consts.PIXELS);
				break;
			case Consts.DIREITA:
				p = hero.moveRight(Consts.CELL_SIDE- Consts.PIXELS);
				break;

			}
			if (p.size() == 0 || algumPersonagemTransponivel(p)) {
				estaComHeroi = false;
				jaEntrouHeroi = true;
			} else {
				hero.setPosicao(pPosicao);
			}
		}

	}

	public void autoDesenho() {
		super.autoDesenho();
		timer++;
		if(estaNaAgua && estaComHeroi && !jaEntrouHeroi) {
			jaEntrouHeroi = true;
		}else if (estaNaAgua && jaEntrouHeroi && timer % 5 == 0) {
			andaSobreAgua();
		}
		if (jaEsteveNoAgua && !estaNaAgua) {
			processaTempo();
		}
		if (!estaNaAgua && !jaEsteveNoAgua) {
			destruirDepoisDaAgua();
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

	private void destruirDepoisDaAgua() {
		if (timer % 60 == 0) {
			this.setImage(1, 11);
		}
		if (timer == 80) {
			monstroAnteAguar.setPosicao(this.pPosicao.getLinha(), this.pPosicao.getColuna());
			Desenho.acessoATelaDoJogo().removePersonagem(this);
			Desenho.acessoATelaDoJogo().addPersonagem(monstroAnteAguar);
		}

	}

	private boolean algumPersonagemEhRio(ArrayList<Personagem> personagens) {
		for (Personagem p : personagens) {
			if (p instanceof Rio) {
				return true;
			}
		}
		return false;

	}

	private void andaSobreAgua() {
		if (andaVerticalmente) {
			andarSobreAguaVertical();
		} else {
			andarSobreAguaHorizontal();
		}
		Hero hero = Desenho.acessoATelaDoJogo().getHero();
		if (hero.pPosicao.igual(pPosicao)) {
			hero.pPosicao.copia(pPosicao);
		}
	}

	private void andarSobreAguaHorizontal() {
		ArrayList<Personagem> p = null;
		if (this.pPosicao.getColuna() < Consts.RES * Consts.CELL_SIDE / 2) {
			p = moveRight();
			if (p != null
					&& posicaoValida(new Posicao(pPosicao.getLinha(), pPosicao.getColuna() + Consts.CELL_SIDE - 5))) {
				pPosicao.moveRight();
			} else {
				processaTempo();
				timer = 0;
			}
		} else {
			p = moveLeft();
			if (p != null && posicaoValida(new Posicao(pPosicao.getLinha(), pPosicao.getColuna() - Consts.CELL_SIDE))) {
				pPosicao.moveLeft();
			} else {
				processaTempo();
				timer = 0;
			}
		}

	}

	private void andarSobreAguaVertical() {
		System.out.println("Andou vertical");
		ArrayList<Personagem> p = moveDown();

		if (p != null && posicaoValida(new Posicao(pPosicao.getLinha() + Consts.CELL_SIDE, pPosicao.getColuna()))) {
			pPosicao.moveDown();
		} else {
			processaTempo();
			timer = 0;
		}
	}

	private void processaTempo() {
		estaNaAgua = false;
		if (timer % 5 == 0 && !estaComHeroi) {
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
		} else if (estaComHeroi) {
			timer = 0;
		}

		timer++;
	}

	private boolean posicaoValida(Posicao posicao) {
		boolean valido = false;
		ArrayList<Personagem> personagens = Desenho.acessoATelaDoJogo().getPersonagens();
		for (int i = 1; i < personagens.size(); i++) {
			Personagem p = personagens.get(i);

			if (p.pPosicao.igual(posicao) && (p instanceof Rio) && !p.equals(this)) {
				System.out.println("Caso 1");
				valido = true;
			} else if (p.pPosicao.igual(pPosicao) && !(p instanceof Rio) && !p.equals(this)) {
				System.out.println(p.toString());
				valido = false;
			}
		}

		System.out.println(valido);
		return valido;
	}

	@Override
	public void acabouAsVidas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void morreuPorTiro() {
		if (!this.estaNaAgua)
			destruidaPorHeroi = true;
	}

}
