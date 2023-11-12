package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Tatu extends Personagem implements IterageComHeroi, Monstro {

	private static final long serialVersionUID = 6695476939422826179L;
	private int direcao = Consts.BAIXO;
	private int iDirecao = 0;
	private boolean estaRolando = false;
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
		int passo = 8;

		int direcaoLinha = heroiLinha - linha;
		int direcaoColuna = heroiColuna - coluna;

		
		if(direcaoLinha == 0) {
			rolar(direcaoLinha, direcaoColuna);
		}else if(direcaoColuna == 0) {
			rolar(direcaoLinha, direcaoColuna);
		}else if (iDirecao % passo == 0) {
			updateDirection(direcaoLinha, direcaoColuna);
		} else {
			if (moveInDirection() instanceof Tatu) {
				passo = 90;
			} else {
				passo = 8;
			}
		}

		iDirecao++;
		super.autoDesenho();
	}

	private Personagem updateDirection(int direcaoLinha, int direcaoColuna) {
		Personagem pIteragido = null;
//		if (Math.abs(direcaoLinha) > Math.abs(direcaoColuna)) {
		if (direcaoLinha > 0) {
			pIteragido = moveDown();
			setDirection(pIteragido, Consts.CIMA, Consts.BAIXO);
		} else if (direcaoLinha < 0) {
			pIteragido = moveUp();
			setDirection(pIteragido, Consts.BAIXO, Consts.CIMA);
		}
//		} else {
		if (direcaoColuna > 0) {
			pIteragido = moveRight();
			setDirection(pIteragido, Consts.ESQUERDA, Consts.DIREITA);
		} else if (direcaoColuna < 0) {
			pIteragido = moveLeft();
			setDirection(pIteragido, Consts.DIREITA, Consts.ESQUERDA);
		}
//		}
		return pIteragido;
	}

	private void setDirection(Personagem moveResult, int directionTrue, int directionFalse) {
		if (moveResult != null) {
			direcao = directionTrue;
		} else {
			direcao = directionFalse;
		}
	}

	private Personagem moveInDirection() {
		Personagem p = null;
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
		return p;
	}

    public Personagem moveUp() {
    	
    	this.setImage(numeroDoSprite % 2,16);
    	numeroDoSprite++;
    	Personagem pIteragido = super.moveUp();
    	
        return pIteragido;
    }

    public Personagem moveDown() {
    	this.setImage(numeroDoSprite % 3, 14);
    	numeroDoSprite++;
    	Personagem pIteragido  = super.moveDown();
    	
        return pIteragido;
    }

    public Personagem moveRight() {
    	this.setImage(numeroDoSprite % 2, 17);
    	numeroDoSprite++;
    	Personagem pIteragido  = super.moveRight();
    	
        return pIteragido;
    }

    public Personagem moveLeft() {
    	this.setImage(numeroDoSprite % 2, 15);
    	numeroDoSprite++;
    	Personagem pIteragido  = super.moveLeft();
    	
        return pIteragido;
    }    
    public void rolar(int direcaoLinha, int direcaoColuna) {
    	System.out.println("ROLANDO");
    	this.setImage(numeroDoSprite % 2, 18);
    	
    }
	public void acabouAsVidas() {

	}

}
