package Modelo;

import java.util.ArrayList;

import Auxiliar.Desenho;

public class Rio extends Personagem {

	private static final long serialVersionUID = 6818233384988168250L;
	
	public Rio() {
		super(6, 9);
		super.bTransponivel = false;
	}
	public void autoDesenho() {
		super.autoDesenho();
//		ArrayList<Personagem> personagens = Desenho.acessoATelaDoJogo().getPersonagens();
//		for(Personagem p : personagens) {
//			if(p.pPosicao.igual(pPosicao) && p instanceof Moeda) {
//				bTransponivel = true;
//				return;
//			}
//		}
//		bTransponivel = false;
	}
	
}
