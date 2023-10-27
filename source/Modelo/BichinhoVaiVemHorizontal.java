package Modelo;

public class BichinhoVaiVemHorizontal extends Personagem{
    private static final long serialVersionUID = 2928618827555119553L;
	private boolean bRight;

    public BichinhoVaiVemHorizontal() {
        super(0,12);
        bRight = true;
    }
    public void autoDesenho(){
        if(bRight)
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
        else
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);           

        super.autoDesenho();
        bRight = !bRight;
    }
}
