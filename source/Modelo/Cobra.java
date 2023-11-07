package Modelo;

import java.util.ArrayList;
import interfaces.IterageComHeroi;

public class Cobra extends Personagem implements IterageComHeroi{
    private static final long serialVersionUID = 2928618827555119553L;

    public Cobra() {
        super(0,12);
        super.bTransponivel = false;
    }
    public boolean interageHeroi(Hero hero,ArrayList<Personagem> umaFase) {
    	Personagem moeda = new Moeda(hero.getLado());
    	moeda.setPosicao(this.pPosicao);    	
    	umaFase.add(moeda);
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.remove(this);
	    return true;
    }
}
