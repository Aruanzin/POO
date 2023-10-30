package Modelo;

import Auxiliar.Desenho;

public class Fogo extends Personagem{
            
	private static final long serialVersionUID = -1085753963596546867L;

	public Fogo(int positionX, int positionY) {
        super(0,21);
        this.bMortal = true;
        this.setPosicao(positionX, positionY);
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(!this.moveRight() || !this.validaPosicao())
            Desenho.acessoATelaDoJogo().removePersonagem(this);
    }
    
}
