package Controler;

import Modelo.Personagem;
import Modelo.Hero;
import java.util.ArrayList;
import interfaces.IterageComHeroi;
import Modelo.Rio;

public class ControleDeJogo {
	public void desenhaTudo(ArrayList<Personagem> e) {
		for (int i = 1; i < e.size(); i++) {
			e.get(i).autoDesenho();
		}
		e.get(0).autoDesenho();
	}

	public void processaTudo(Fase fase) {
		ArrayList<Personagem> umaFase = fase.getPersonagens();
		Hero hero = (Hero) umaFase.get(0);
		Personagem pIesimoPersonagem;
		for (int i = 1; i < umaFase.size(); i++) {
			pIesimoPersonagem = umaFase.get(i);
			if (hero.getPosicao().igual(pIesimoPersonagem.getPosicao())){
				if (pIesimoPersonagem instanceof IterageComHeroi) {
					/* pIesimoPersonagem implementa a interface IterageComHeroi */
					IterageComHeroi iterageComHeroi = (IterageComHeroi) pIesimoPersonagem;
					System.out.println(pIesimoPersonagem.toString());
					if (!iterageComHeroi.interageHeroi(hero, fase)) {
						hero.voltaAUltimaPosicao();
					}
				}
			}
		}
	}

}
