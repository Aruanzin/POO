package Modelo;

import java.util.ArrayList;

public class Rio extends Personagem {

	private static final long serialVersionUID = 6818233384988168250L;
	
	public Rio() {
		super(6, 9);
		super.bTransponivel = false;
	}
	
	public void iterageMoeda(ArrayList<Personagem> umaFase) {
		for(Personagem p : umaFase){
			if(p.pPosicao.igual(pPosicao) && p instanceof Moeda){
				p.setPosicao(this.pPosicao);
				System.out.println("AMSDFOASMDFO");
			}
			
		}
	}
}
