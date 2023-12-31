package Modelo;

import Auxiliar.Desenho;
import Controler.Fase;
import interfaces.IterageComHeroi;

public class Fogo extends PersonagemMovel implements IterageComHeroi {

	private static final long serialVersionUID = -1085753963596546867L;

	public Fogo(int positionX, int positionY, int lado, boolean mortalAoHeroi) {
		super(lado, 21);
		this.bMortal = mortalAoHeroi;
		this.ladoVirado = lado;
		this.setPosicao(positionX, positionY);
		this.bTransponivel = false;
 	}

	public void quandoBater() {
		System.out.println("Fogo bateu");
		setImage(ladoVirado, 22);
		Desenho.acessoATelaDoJogo().removePersonagem(this);
	}

	@Override
	public Personagem interageHeroi(Hero hero, Fase umaFase) {
		// TODO Auto-generated method stub
		if (bMortal) {
			hero.setNumeroVidasRestantes(-1);
			umaFase.removePersonagem(this);
		}
		return this;
	}

}
