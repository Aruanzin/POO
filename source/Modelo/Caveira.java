package Modelo;

import java.util.ArrayList;
import java.util.Random;

import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Caveira extends Personagem implements IterageComHeroi, Monstro {
	private static final long serialVersionUID = 20231107083000L;

	private int numeroDoSprite = 0;
	private boolean podeMexer = false;
	private int direcao = 0;
	private boolean trocaDir = false;

	public Caveira() {
		super(0, 13);
		super.bTransponivel = false;
	}

	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		Personagem moeda = new Moeda((Personagem) this);
		moeda.setPosicao(this.pPosicao);
		umaFase.addPersonagem(moeda);
		hero.setNumeroVidasRestantes(-1);
		umaFase.removePersonagem(this);
		return this;
	}

	@Override
	public void acabouAsVidas() {
		podeMexer = true;
	}

	public void autoDesenho() {
		super.autoDesenho();
		if (podeMexer == true) {
			super.setImage(numeroDoSprite % 2, 13);
			numeroDoSprite++;
			Hero heroi= Desenho.acessoATelaDoJogo().getHero();
			if(heroi.getPosicao().getLinha() == pPosicao.getLinha() || heroi.getPosicao().getColuna() == pPosicao.getColuna()) {
				moverEmDirecaoAoHeroi(heroi);
			}
			if (trocaDir == true) {
				Random random = new Random();
				int anterior;
				do {
					anterior = direcao;
					direcao = random.nextInt(4);
				} while (direcao == anterior);
			}
			ArrayList<Personagem> p;
			switch (direcao) {
			case 0:
				p = moveUp();
				trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
				break;
			case 1:
				p = moveDown();
				trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
				break;
			case 2:
				p = moveLeft();
				trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
				break;
			case 3:
				p = moveRight();
				trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
				break;
			default:
				break;
			}
		}
	}

	public void morreuPorTiro() {
		Fase umaFase = Desenho.acessoATelaDoJogo().getFase();
		Personagem moeda = new Moeda((Personagem) this);
		moeda.setPosicao(this.pPosicao);
		umaFase.addPersonagem(moeda);
		umaFase.removePersonagem(this);
	}

	public void moverEmDirecaoAoHeroi(Hero heroi) {
	    int linhaHeroi = heroi.getPosicao().getLinha();
	    int colunaHeroi = heroi.getPosicao().getColuna();

	    int linhaCaveira = pPosicao.getLinha();
	    int colunaCaveira = pPosicao.getColuna();

	    if (linhaCaveira > linhaHeroi) {
	        // Mover para cima
	        ArrayList<Personagem> p = moveUp();
	        trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
	    } else if (linhaCaveira < linhaHeroi) {
	        // Mover para baixo
	        ArrayList<Personagem> p = moveDown();
	        trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
	    } else if (colunaCaveira > colunaHeroi) {
	        // Mover para a esquerda
	        ArrayList<Personagem> p = moveLeft();
	        trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
	    } else if (colunaCaveira < colunaHeroi) {
	        // Mover para a direita
	        ArrayList<Personagem> p = moveRight();
	        trocaDir = p.size() != 0 && algumPersonagemNaoTransponivel(p);
	    }
	}
}
