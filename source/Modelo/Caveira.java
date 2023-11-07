package Modelo;

<<<<<<< HEAD
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Caveira extends Personagem implements IterageComHeroi, Monstro {

	private static final long serialVersionUID = 6516657816035787688L;

	public Caveira() {
		super(0, 13);
		this.bTransponivel = false;
	}

	public boolean interageHeroi(Hero hero, Fase umaFase) {
		return true;
	}

=======
import java.util.ArrayList;

import interfaces.IterageComHeroi;

public class Caveira extends Personagem implements IterageComHeroi{
    private static final long serialVersionUID = 20231107083000L;

    public Caveira() {
        super(0,13);
        super.bTransponivel = false;
    }
    public boolean interageHeroi(Hero hero,ArrayList<Personagem> umaFase) {
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.remove(this);
	    return true;
    }
>>>>>>> 4fc98d404d595275a309b6496e5ea33ed9141fc9
}
