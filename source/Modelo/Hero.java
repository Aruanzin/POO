package Modelo;

import Auxiliar.Desenho;

public class Hero extends Personagem{
    private static final long serialVersionUID = 8078437919817023211L;
    private int numeroVidasRestantes;
    private boolean estaVivo = true;
    private boolean passouFase = false;
    
	public Hero() {
        super(0,0);
    }	
	
	public int getNumeroVidasRestantes() {
		return numeroVidasRestantes;
	}

	public void setNumeroVidasRestantes(int numeroVidasRestantes) {
		if(this.numeroVidasRestantes < 3)
			this.numeroVidasRestantes += numeroVidasRestantes;
		if(this.numeroVidasRestantes < 0) {
			this.estaVivo = false;
		}
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
        if(super.moveUp())
            return validaPosicao();
        return false;
    }

    public boolean moveDown() {
    	System.out.println("MOVE_SE");
        if(super.moveDown())
            return validaPosicao();
        return false;
    }

    public boolean moveRight() {
        if(super.moveRight())
            return validaPosicao();
        return false;
    }

    public boolean moveLeft() {
        if(super.moveLeft())
            return validaPosicao();
        return false;
    }    
	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}
	public boolean passouFase() {
		return passouFase;
	}

	public void setPassouFase(boolean passouFase) {
		this.passouFase = passouFase;
	}
	public void atirar() {
		Personagem foga= new Fogo(this.getPosicao().getLinha(), this.getPosicao().getColuna());
		foga.autoDesenho();
		Desenho.acessoATelaDoJogo().addPersonagem(foga);
	}
}
