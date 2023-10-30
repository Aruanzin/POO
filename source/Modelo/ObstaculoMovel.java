package Modelo;

import java.util.ArrayList;
import interfaces.IterageComHeroi;
import Auxiliar.Posicao;

public class ObstaculoMovel extends Personagem implements IterageComHeroi {
	private static final long serialVersionUID = 1L;
	
	public ObstaculoMovel() {
		super(0,8);
		super.bTransponivel = false;
	}
	public boolean interageHeroi(Hero hero, ArrayList<Personagem> umaFase) {
		System.out.println("INTERAGIOU COM O HEROI");
		Posicao posicaoHeroi = hero.getPosicaoAnterior();

        int linhaDif = (int) (posicaoHeroi.getLinha() - this.pPosicao.getLinha());
        int colunaDif = (int) (posicaoHeroi.getColuna() - this.pPosicao.getColuna());

        // Move o ObstaculoMovel na mesma direção do herói, evitando sobreposições
        if (Math.abs(linhaDif) > Math.abs(colunaDif)) {
            if (linhaDif > 0) {
                // Move o ObstaculoMovel para baixo
                if(this.moveUp())
                	return validaPosicao();
            } else if (linhaDif < 0) {
                // Move o ObstaculoMovel para cima
                if(this.moveDown())
                	return validaPosicao();
            }
        } else {
            if (colunaDif > 0) {
                // Move o ObstaculoMovel para a direita
                if(this.moveLeft())
                	return validaPosicao();
            } else if (colunaDif < 0) {
                // Move o ObstaculoMovel para a esquerda
                if(this.moveRight())
                	return validaPosicao();
            }
        }
		return false;
	}
}
