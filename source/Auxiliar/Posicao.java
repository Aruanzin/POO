package Auxiliar;

import java.io.Serializable;

public class Posicao implements Serializable {
	private static final long serialVersionUID = 3419395796904834653L;
	private int linha;
	private int coluna;

	private int linhaAnterior;
	private int colunaAnterior;

//    private int pixels = 10;

	public Posicao(int linha, int coluna) {
		this.setPosicao(linha, coluna);
	}

	public boolean setPosicao(int linha, int coluna) {
		if (linha < 0 || linha >= Auxiliar.Consts.RES * Auxiliar.Consts.CELL_SIDE)
			return false;
		linhaAnterior = this.linha;
		this.linha = linha;

		if (coluna < 0 || coluna >= Auxiliar.Consts.RES * Auxiliar.Consts.CELL_SIDE)
			return false;
		colunaAnterior = this.coluna;
		this.coluna = coluna;

		return true;
	}

	public int getLinha() {
		return linha;
	}

	public boolean volta() {
		return this.setPosicao(linhaAnterior, colunaAnterior);
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinhaAnterior() {
		return linhaAnterior;
	}

	public int getColunaAnterior() {
		return colunaAnterior;
	}

	public boolean igual(Posicao posicao) {
	    int desvioPermitido = 5; // Define o desvio máximo permitido em pixels

	    for (int i = -desvioPermitido; i <= desvioPermitido; i++) {
	        for (int j = -desvioPermitido; j <= desvioPermitido; j++) {
	            int distanciaLinha = Math.abs(linha + i - posicao.getLinha());
	            int distanciaColuna = Math.abs(coluna + j - posicao.getColuna());
	            
	            if (distanciaLinha <= desvioPermitido && distanciaColuna <= desvioPermitido) {
	                return true;
	            }
	        }
	    }

	    return false;
	}



	public boolean copia(Posicao posicao) {
		return this.setPosicao(posicao.getLinha(), posicao.getColuna());
	}

	public boolean moveUp() {
		return this.setPosicao(this.getLinha() - Consts.PIXELS, this.getColuna());
	}

	public boolean moveDown() {
		return this.setPosicao(this.getLinha() + Consts.PIXELS, this.getColuna());
	}

	public boolean moveRight() {
		return this.setPosicao(this.getLinha(), this.getColuna() + Consts.PIXELS);
	}

	public boolean moveLeft() {
		return this.setPosicao(this.getLinha(), this.getColuna() - Consts.PIXELS);
	}
}
