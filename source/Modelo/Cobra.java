package Modelo;

import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Cobra extends Personagem implements IterageComHeroi, Monstro{
    private static final long serialVersionUID = 2928618827555119553L;
    private int timer = 0;

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

    public void acabouAsVidas() {
		
	}

	public void morreuPorTiro() {
		Fase umaFase = Desenho.acessoATelaDoJogo().getFase();
		Personagem moeda = new Moeda((Personagem)this);
    	moeda.setPosicao(this.pPosicao);    	
    	umaFase.addPersonagem(moeda);
	    umaFase.removePersonagem(this);
	}
	public void autoDesenho() {
		super.autoDesenho();
		Hero hero = Desenho.acessoATelaDoJogo().getHero();
		
		if(hero.getPosicao().getLinha() == pPosicao.getLinha()) {
			if(hero.getPosicao().getColuna() < pPosicao.getColuna()) {
				setImage(0,12);
			}else {
				setImage(3,12);
			}
		}else if(timer %5 == 0){
			if(hero.getPosicao().getColuna() < pPosicao.getColuna()) {
				setImage(1,12);
			}else {
				setImage(2,12);
			}
		}
		timer ++;
	}
}
