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
	    // Verifique se há sobreposição entre os retângulos delimitadores dos objetos.
	    if (this.getLinha() + this.getAltura() > posicao.getLinha() &&
	        this.getLinha() < posicao.getLinha() + posicao.getAltura() &&
	        this.getColuna() + this.getLargura() > posicao.getColuna() &&
	        this.getColuna() < posicao.getColuna() + posicao.getLargura()) {
	        // Há uma sobreposição, o que indica uma colisão.
	        return true;
	    }
	    // Não há colisão.
	    return false;
	}


	public boolean copia(Posicao posicao) {
		return this.setPosicao(posicao.getLinha(), posicao.getColuna());
	}

	public int getLargura() {
		return Consts.CELL_SIDE;
	}
	public int getAltura() {
		return Consts.CELL_SIDE;
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
