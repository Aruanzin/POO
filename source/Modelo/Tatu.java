package Modelo;


import java.util.Random;
import Controler.Fase;
import interfaces.IterageComHeroi;
import interfaces.Monstro;

public class Tatu extends Personagem implements IterageComHeroi, Monstro{
	
    private boolean podeMexer = false;
    private int direcao = 0;
    private boolean trocaDir = false;
    private int numeroDoSprite = 0;
    
	public Tatu(){
		super(0,14);
        super.bTransponivel = false;
	}
	public boolean interageHeroi(Hero hero,Fase umaFase) {
    	hero.setNumeroVidasRestantes(-1);
	    umaFase.removePersonagem(this);
	    return true;
    }
    public void acabouAsVidas() {
		podeMexer = true;
	}
	
	public void autoDesenho() {
		super.autoDesenho();
		if(podeMexer == true) {
			super.setImage(numeroDoSprite % 2, 14);
			numeroDoSprite++;
			if(trocaDir == true) {
				Random random = new Random();
				int anterior;
				do {
					anterior = direcao;			
					direcao = random.nextInt(4);
				}while(direcao == anterior);
			}
			switch(direcao) {
				case 0:
					trocaDir = !(this.moveUp());
					this.setImage(numeroDoSprite % 2, 15);
					break;
				case 1:
					trocaDir = !(this.moveDown());
					this.setImage(numeroDoSprite % 2, 16);
					break;
				case 2:
					trocaDir = !(this.moveLeft());
					this.setImage(numeroDoSprite % 2, 17);
					break;
				case 3:
					trocaDir = !(this.moveRight());
					this.setImage(numeroDoSprite % 2, 18);
					break;
				default:
					break;
			}
		}
	}
	
}
