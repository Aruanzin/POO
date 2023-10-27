package Modelo;

import Auxiliar.Desenho;

public class Fogo extends Personagem{
            
    /**
	 * 
	 */
	private static final long serialVersionUID = -1085753963596546867L;

	public Fogo(String sNomeImagePNG) {
        super(0,0);
        this.bMortal = true;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(!this.moveRight())
            Desenho.acessoATelaDoJogo().removePersonagem(this);
    }
    
}
