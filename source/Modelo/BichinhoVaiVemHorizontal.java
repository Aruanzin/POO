package Modelo;

public class BichinhoVaiVemHorizontal extends Personagem{
    private boolean bRight;

    public BichinhoVaiVemHorizontal(String sNomeImagePNG) {
        super(sNomeImagePNG);
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
