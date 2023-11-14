package Modelo;

import java.util.Random;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class DinoRosa extends Personagem implements IterageComHeroi, Monstro{

	private static final long serialVersionUID = -3987983645885342388L;
	
	private static int numeroDoSprite = 0;
    private boolean podeAtirar = true;
    private int direcao = 0;
    private boolean trocaDir = false;
    private int ladoVirado;
    private static int nDeEstancia;
    private int time;

    
    public DinoRosa() {
		super(escolherFace(), 19);
		numeroDoSprite++;
		ladoVirado = nDeEstancia;
		super.bTransponivel = false;
		// TODO Auto-generated constructor stub
	}
	
    public static int escolherFace() {
		switch (numeroDoSprite) {
			case 0:
				nDeEstancia = Consts.BAIXO;
				return 0;
			case 1: 
				nDeEstancia = Consts.ESQUERDA;
				return 1;
			case 2:
				nDeEstancia = Consts.DIREITA;
				return 3;
			case 3:
				nDeEstancia = Consts.CIMA;
				return 2;
			default:
				return -1;
		}
	}

	@Override
	public void acabouAsVidas() {
		// TODO Auto-generated method stub
		podeAtirar = true;
	}

	@Override
	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		Personagem moeda = new Moeda((Personagem) this);
    	moeda.setPosicao(this.pPosicao);    	
    	umaFase.addPersonagem(moeda);
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.removePersonagem(this);
	    return this;
	}
	
	public void autoDesenho() {
		super.autoDesenho();
		time++;
		if(podeAtirar && time % 20 == 0) {
			super.setImage(ladoVirado, 20);
			
			Hero heroi = (Hero) Desenho.acessoATelaDoJogo().getPersonagens().get(0);

		    int heroiLinha = heroi.pPosicao.getLinha();
		    int heroiColuna = heroi.pPosicao.getColuna();

		    int linha = pPosicao.getLinha();
		    int coluna = pPosicao.getColuna();
		    
		    // Verifica se o herói está na mesma linha
		    switch(ladoVirado) {
		    	case Consts.BAIXO:
		    		if(coluna > heroiColuna - 30 && coluna < heroiColuna + 30) {
		    			if(linha < heroiLinha) {
		    				System.out.println("ATIROU PRA BAIXO");
		    				atirar();	
		    			}
		    		}
		    		break;
		    	case Consts.CIMA:
		    		if(coluna > heroiColuna - 30 && coluna < heroiColuna + 30) {
		    			if(linha > heroiLinha) {
			    			System.out.println("ATIROU PRA CIMA");
			    			atirar();
		    			}
		    		}
		    		break;
		    	case Consts.DIREITA:
		    		if(linha > heroiLinha - 30 && linha < heroiLinha + 30) {
		    			if(coluna < heroiColuna) {
			    			System.out.println("ATIROU PRA DIREITA");
			    			atirar();
		    			}
		    		}

	    			break;
		    	case Consts.ESQUERDA:
		    		if(linha > heroiLinha - 30 && linha < heroiLinha + 30) {
		    			if(coluna > heroiColuna) {
			    			System.out.println("ATIROU PRA ESQUERDA");
			    			atirar();
		    			}
		    		}
		    		break;
		    }
		}
	}
	
	public void atirar() {
		Personagem foga= new Fogo(this.getPosicao().getLinha(), this.getPosicao().getColuna(), ladoVirado, true);

		
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
		
		Desenho.acessoATelaDoJogo().addPersonagem(foga);
		
	}
	public void morreuPorTiro() {
		Fase umaFase = Desenho.acessoATelaDoJogo().getFase();
		Personagem moeda = new Moeda((Personagem) this);
    	moeda.setPosicao(this.pPosicao);    	
    	umaFase.addPersonagem(moeda);
	    umaFase.removePersonagem(this);
	}
}
