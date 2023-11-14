package Modelo;

import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Cobra extends Personagem implements IterageComHeroi, Monstro{
    private static final long serialVersionUID = 2928618827555119553L;

    public Cobra() {
        super(0,12);
        super.bTransponivel = false;
    }
    public Personagem interageHeroi(Hero hero,Fase umaFase) {
    	Personagem moeda = new Moeda((Personagem) this);
    	moeda.setPosicao(this.pPosicao);    	
    	umaFase.addPersonagem(moeda);
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.removePersonagem(this);
	    return this;
    }
	@Override
	public void acabouAsVidas() {
		// TODO Auto-generated method stub
		
	}

	public void morreuPorTiro() {
		Fase umaFase = Desenho.acessoATelaDoJogo().getFase();
		Personagem moeda = new Moeda((Personagem)this);
    	moeda.setPosicao(this.pPosicao);    	
    	umaFase.addPersonagem(moeda);
	    umaFase.removePersonagem(this);
	}
}
