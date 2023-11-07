package Modelo;

import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Cobra extends Personagem implements IterageComHeroi, Monstro{
    private static final long serialVersionUID = 2928618827555119553L;

    public Cobra() {
        super(0,12);
        super.bTransponivel = false;
    }
    public boolean interageHeroi(Hero hero,Fase umaFase) {
    	Personagem moeda = new Moeda(hero.getLado());
    	moeda.setPosicao(this.pPosicao);    	
    	umaFase.addPersonagem(moeda);
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.removePersonagem(this);
	    return true;
    }
}
