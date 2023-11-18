package Modelo;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Tatu extends Personagem implements IterageComHeroi, Monstro {

	private static final long serialVersionUID = 6695476939422826179L;
	private int direcao = Consts.BAIXO;
	private int iDirecao = 0;
	private boolean podeMudarDirecao = true;
	private int passo = 8;

	private int numeroDoSprite = 0;

	public Tatu() {
		super(0, 14);
		super.bTransponivel = false;
	}

	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		hero.setNumeroVidasRestantes(-1);
		umaFase.removePersonagem(this);
		return this;
	}

	public void autoDesenho() {
		Hero heroi = (Hero) Desenho.acessoATelaDoJogo().getPersonagens().get(0);
		int heroiLinha = heroi.pPosicao.getLinha();
		int heroiColuna = heroi.pPosicao.getColuna();

		int linha = pPosicao.getLinha();
		int coluna = pPosicao.getColuna();

		int direcaoLinha = heroiLinha - linha;
		int direcaoColuna = heroiColuna - coluna;

//		if(estaRolando) {
//			
//			return;
//		}

//		if (direcaoLinha == 0) {
//			rolar(direcaoLinha, direcaoColuna);
//		} else if (direcaoColuna == 0) {
//			rolar(direcaoLinha, direcaoColuna);
//		} else 
		if (iDirecao % passo == 0) {
			updateDirection(direcaoLinha, direcaoColuna);
			podeMudarDirecao = true;
		} else {
			Personagem p = moveInDirection();
			if (p instanceof Tatu) {
				direcao = changeDirection();
				passo = 24;
				podeMudarDirecao = false;
				((Tatu) p).setPasso(passo, podeMudarDirecao);
			} else if (podeMudarDirecao){
				passo = 8;
			}
		}

		iDirecao++;
		super.autoDesenho();
	}
	
	public void setPasso(int passo, boolean podeMudar) {
		this.passo = passo;
		podeMudarDirecao = podeMudar;
				
	}

	private Personagem updateDirection(int direcaoLinha, int direcaoColuna) {
		ArrayList<Personagem> pIteragido = null;
//		if (Math.abs(direcaoLinha) > Math.abs(direcaoColuna)) {
		if (direcaoLinha > 0) {
			pIteragido = moveDown();
			setDirection(pIteragido.get(0), Consts.CIMA, Consts.BAIXO);
		} else if (direcaoLinha < 0) {
			pIteragido = moveUp();
			setDirection(pIteragido.get(0), Consts.BAIXO, Consts.CIMA);
		}
//		} else {
		if (direcaoColuna > 0) {
			pIteragido = moveRight();
			setDirection(pIteragido.get(0), Consts.ESQUERDA, Consts.DIREITA);
		} else if (direcaoColuna < 0) {
			pIteragido = moveLeft();
			setDirection(pIteragido.get(0), Consts.DIREITA, Consts.ESQUERDA);
		}
//		}
		return pIteragido.get(0);
	}

	private void setDirection(Personagem moveResult, int directionTrue, int directionFalse) {
		if (moveResult != null) {
			direcao = directionTrue;
		} else {
			direcao = directionFalse;
		}
	}

	private int changeDirection() {
		switch (direcao) {
		case Consts.DIREITA:
			return Consts.ESQUERDA;
		case Consts.ESQUERDA:
			return Consts.DIREITA;
		case Consts.CIMA:
			return Consts.BAIXO;
		case Consts.BAIXO:
			return Consts.CIMA;
		}
		return -1;
	}

	private Personagem moveInDirection() {
		ArrayList<Personagem> p = null;
		switch (direcao) {
		case Consts.DIREITA:
			p = moveRight();
			break;
		case Consts.ESQUERDA:
			p = moveLeft();
			break;
		case Consts.CIMA:
			p = moveUp();
			break;
		case Consts.BAIXO:
			p = moveDown();
			break;
		}
		return p.get(0);
	}

	public ArrayList<Personagem> moveUp() {

		this.setImage(numeroDoSprite % 2, 16);
		numeroDoSprite++;
		ArrayList<Personagem> pIteragido = super.moveUp();

		return pIteragido;
	}

	public ArrayList<Personagem> moveDown() {
		this.setImage(numeroDoSprite % 3, 14);
		numeroDoSprite++;
		ArrayList<Personagem> pIteragido = super.moveDown();

		return pIteragido;
	}

	public ArrayList<Personagem> moveRight() {
		this.setImage(numeroDoSprite % 2, 17);
		numeroDoSprite++;
		ArrayList<Personagem> pIteragido = super.moveRight();

		return pIteragido;
	}

	public ArrayList<Personagem> moveLeft() {
		this.setImage(numeroDoSprite % 2, 15);
		numeroDoSprite++;
		ArrayList<Personagem> pIteragido = super.moveLeft();

		return pIteragido;
	}

	public void rolar(int direcaoLinha, int direcaoColuna) {
		System.out.println("ROLANDO");
		this.setImage(numeroDoSprite % 2, 18);

	}

	public void acabouAsVidas() {

	}

	public void morreuPorTiro() {
		Fase umaFase = Desenho.acessoATelaDoJogo().getFase();
		Personagem moeda = new Moeda((Personagem) this);
		moeda.setPosicao(this.pPosicao);
		umaFase.addPersonagem(moeda);
		umaFase.removePersonagem(this);
	}

}
