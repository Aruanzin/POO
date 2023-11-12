package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Fase;
import Controler.Tela;
import interfaces.IterageComHeroi;

public class Hero extends Personagem{
    private static final long serialVersionUID = 8078437919817023211L;
    private int numeroVidasRestantes;
    private boolean passouFase = false;
    private int numeroDoSprite = 0;
    private int ladoVirado = Consts.BAIXO;
    
	public Hero() {
        super(0,0);
    }	
	
	public int getNumeroVidasRestantes() {
		return numeroVidasRestantes;
	}

	public boolean setNumeroVidasRestantes(int numeroVidasRestantes) {
		if(this.numeroVidasRestantes < 3) {
			this.numeroVidasRestantes += numeroVidasRestantes;
		}
		if(this.numeroVidasRestantes < 0) {
			morreu();
			System.out.println("MORREU");
			return false;
		}
		return true;
	}
        
    public boolean setPosicao(int linha, int coluna){
        if(this.pPosicao.setPosicao(linha, coluna)){
            return true;
        }
        return false;       
    }

    
    public Personagem moveUp() {
    	this.setImage(numeroDoSprite % 3,2);
    	numeroDoSprite++;
    	ladoVirado = Consts.CIMA;
    	Personagem pIteragido = super.moveUp();
    	if(pIteragido instanceof IterageComHeroi) {
    		((IterageComHeroi) pIteragido).interageHeroi(this, Desenho.acessoATelaDoJogo().getFase());
    	}
        return pIteragido;
    }

    public Personagem moveDown() {
    	this.setImage(numeroDoSprite % 3, 0);
    	ladoVirado = Consts.BAIXO;
    	numeroDoSprite++;
    	Personagem pIteragido  = super.moveDown();
    	if(pIteragido instanceof IterageComHeroi) {
    		((IterageComHeroi) pIteragido).interageHeroi(this, Desenho.acessoATelaDoJogo().getFase());
    	}
        return pIteragido;
    }

    public Personagem moveRight() {
    	this.setImage(numeroDoSprite % 3, 3);
    	ladoVirado = Consts.DIREITA;
    	numeroDoSprite++;
    	Personagem pIteragido  = super.moveRight();
    	if(pIteragido instanceof IterageComHeroi) {
    		((IterageComHeroi) pIteragido).interageHeroi(this, Desenho.acessoATelaDoJogo().getFase());
    	}
        return pIteragido;
    }

    public Personagem moveLeft() {
    	this.setImage(numeroDoSprite % 3, 1);
    	numeroDoSprite++;
    	ladoVirado = Consts.ESQUERDA;
    	Personagem pIteragido  = super.moveLeft();
    	if(pIteragido instanceof IterageComHeroi) {
    		((IterageComHeroi) pIteragido).interageHeroi(this, Desenho.acessoATelaDoJogo().getFase());
    	}
        return pIteragido;
    }    
	public void morreu() {
		Desenho.acessoATelaDoJogo().getPersonagens().clear();
		this.setImage(0, 4);
	}
	public boolean passouFase() {
		return passouFase;
	}
	public int getLado() {
		return ladoVirado;
	}
	public void setPassouFase(boolean passouFase) {
		this.passouFase = passouFase;
	}
	public void atirar() {
		Personagem foga= new Fogo(this.getPosicao().getLinha(), this.getPosicao().getColuna(), ladoVirado);
		Desenho.acessoATelaDoJogo().addPersonagem(foga);
	}
}
