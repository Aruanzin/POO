package Modelo;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import interfaces.IterageComHeroi;

public class Hero extends Personagem {
	private static final long serialVersionUID = 8078437919817023211L;
	private int numeroVidasRestantes;
	private boolean passouFase = false;
	private int numeroDoSprite = 0;
	private int ladoVirado = Consts.BAIXO;

	public Hero() {
		super(0, 0);
	}

	public int getNumeroVidasRestantes() {
		return numeroVidasRestantes;
	}

	public boolean setNumeroVidasRestantes(int numeroVidasRestantes) {
		if (this.numeroVidasRestantes < 3) {
			this.numeroVidasRestantes += numeroVidasRestantes;
		}
		if (this.numeroVidasRestantes < 0) {
			morreu();
			System.out.println("MORREU");
			return false;
		}
		return true;
	}

	public boolean setPosicao(int linha, int coluna) {
		if (this.pPosicao.setPosicao(linha, coluna)) {
			return true;
		}
		return false;
	}

	private void interageComHeroi(ArrayList<Personagem> p) {
		for (int i = 0; i < p.size(); i++) {
			Personagem pIteragido = p.get(i);
			if (pIteragido instanceof IterageComHeroi) {
				((IterageComHeroi) pIteragido).interageHeroi(this, Desenho.acessoATelaDoJogo().getFase());
			}
		}
		System.out.println(p.toString());
	}

	public ArrayList<Personagem> moveUp() {
		this.setImage(numeroDoSprite % 3, 2);
		numeroDoSprite++;
		ladoVirado = Consts.CIMA;

		ArrayList<Personagem> pIteragido = null;
		pIteragido = super.moveUp();
		interageComHeroi(pIteragido);
		return pIteragido;
	}

	public ArrayList<Personagem> moveDown() {
		this.setImage(numeroDoSprite % 3, 0);
		ladoVirado = Consts.BAIXO;
		numeroDoSprite++;

		ArrayList<Personagem> pIteragido = null;
		pIteragido = super.moveDown();
		interageComHeroi(pIteragido);
		return pIteragido;
	}

	public ArrayList<Personagem> moveRight() {
		this.setImage(numeroDoSprite % 3, 3);
		ladoVirado = Consts.DIREITA;
		numeroDoSprite++;

		ArrayList<Personagem> pIteragido = null;
		pIteragido = super.moveRight();
		interageComHeroi(pIteragido);
		return pIteragido;
	}

	public ArrayList<Personagem> moveLeft() {
		this.setImage(numeroDoSprite % 3, 1);
		numeroDoSprite++;
		ladoVirado = Consts.ESQUERDA;
		ArrayList<Personagem> pIteragido = null;
		pIteragido = super.moveLeft();
		interageComHeroi(pIteragido);
		return pIteragido;
	}

	public void morreu() {
		Desenho.acessoATelaDoJogo().getPersonagens().clear();
		this.setImage(0, 4);
	}

	public boolean passouFase() {
		return passouFase;
	}

	public int getLado() {
		return ladoVirado;
	}

	public void setPassouFase(boolean passouFase) {
		this.passouFase = passouFase;
	}

	public void atirar() {
		Personagem foga = new Fogo(this.getPosicao().getLinha(), this.getPosicao().getColuna(), ladoVirado, false);

		switch (ladoVirado) {

		case Consts.BAIXO:
			foga.setPosicao(pPosicao.getLinha() + Consts.CELL_SIDE, pPosicao.getColuna());
			break;
		case Consts.CIMA:
			foga.setPosicao(pPosicao.getLinha() - Consts.CELL_SIDE, pPosicao.getColuna());
			break;
		case Consts.ESQUERDA:
			foga.setPosicao(pPosicao.getLinha(), pPosicao.getColuna() - Consts.CELL_SIDE);
			break;
		case Consts.DIREITA:
			foga.setPosicao(pPosicao.getLinha(), pPosicao.getColuna() + Consts.CELL_SIDE);
			break;

		}
		ArrayList<Personagem> p = foga.ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(), foga.pPosicao);

		if (p.size() == 0 || algumPersonagemTransponivel(p) || temAlgumMonstro(p))
			Desenho.acessoATelaDoJogo().addPersonagem(foga);
	}

}
