package Modelo;

import Controler.Fase;
import interfaces.IterageComHeroi;

public class Caveira extends Personagem implements IterageComHeroi{
    private static final long serialVersionUID = 20231107083000L;

    public Caveira() {
        super(0,13);
        super.bTransponivel = false;
    }
    public boolean interageHeroi(Hero hero,Fase umaFase) {
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.removePersonagem(this);
	    return true;
    }
}
