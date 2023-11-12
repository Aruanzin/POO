package Modelo;

import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;

public class Fogo extends PersonagemMovel implements IterageComHeroi{
            
	private static final long serialVersionUID = -1085753963596546867L;
	
	public Fogo(int positionX, int positionY, int lado) {
        super(lado,21);
        this.bMortal = true;
        this.ladoVirado = lado;
        this.setPosicao(positionX, positionY);
    }

    public void quandoBater() {
    	System.out.println("Fogo bateu");
		Desenho.acessoATelaDoJogo().removePersonagem(this);
    }

	@Override
	public boolean interageHeroi(Hero hero, Fase umaFase) {
		// TODO Auto-generated method stub
		hero.setNumeroVidasRestantes(-1);
	    umaFase.removePersonagem(this);
	    return true;
	}
    
}
