package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;

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
            if (!Desenho.acessoATelaDoJogo().ehPosicaoValida(this)) {
                this.voltaAUltimaPosicao();
            }
            return true;
        }
        return false;       
    }

    
    public boolean moveUp() {
    	System.out.println(numeroDoSprite % 3);
    	this.setImage(numeroDoSprite % 3,2);
    	numeroDoSprite++;
    	ladoVirado = Consts.CIMA;
    	
        if(super.moveUp())
            return validaPosicao();
        return false;
    }

    public boolean moveDown() {
    	this.setImage(numeroDoSprite % 3, 0);
    	ladoVirado = Consts.BAIXO;
    	numeroDoSprite++;
        if(super.moveDown())
            return validaPosicao();
        return false;
    }

    public boolean moveRight() {
    	this.setImage(numeroDoSprite % 3, 3);
    	ladoVirado = Consts.DIREITA;
    	numeroDoSprite++;
        if(super.moveRight())
            return validaPosicao();
        return false;
    }

    public boolean moveLeft() {
    	this.setImage(numeroDoSprite % 3, 1);
    	numeroDoSprite++;
    	ladoVirado = Consts.ESQUERDA;
    	
        if(super.moveLeft()) 
            return validaPosicao();
        return false;
    }    
	public void morreu() {
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
