package Modelo;

public class Obstaculo extends Personagem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5259833026776526311L;
//	private int iContaIntervalos;

	public Obstaculo(int spriteX, int spriteY) {
		super(spriteX, spriteY);
		this.bTransponivel = false;
	}

	public Obstaculo(int spriteX, int spriteY, int spriteW, int spriteH) {
		super(spriteX, spriteY, spriteW, spriteH);
		this.bTransponivel = false;
	}

	public void autoDesenho() {
		super.autoDesenho();

//        this.iContaIntervalos++;
//        if(this.iContaIntervalos == Consts.TIMER){
//            this.iContaIntervalos = 0;
//            Fogo f = new Fogo("fire.png");
//            f.setPosicao(pPosicao.getLinha(),pPosicao.getColuna()+1);
//            Desenho.acessoATelaDoJogo().addPersonagem(f);
//        }
	}
}
