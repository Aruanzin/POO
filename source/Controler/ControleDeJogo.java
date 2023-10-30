package Controler;

import Modelo.Personagem;
import Modelo.Hero;
import java.util.ArrayList;
import interfaces.IterageComHeroi;

public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Personagem> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    public void processaTudo(ArrayList<Personagem> umaFase){
        Hero hero = (Hero)umaFase.get(0);
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
            if(hero.getPosicao().igual(pIesimoPersonagem.getPosicao()))
            	if (pIesimoPersonagem instanceof IterageComHeroi) {
            	    /* pIesimoPersonagem implementa a interface IterageComHeroi */
            	    IterageComHeroi iterageComHeroi = (IterageComHeroi) pIesimoPersonagem;
            	    System.out.println(pIesimoPersonagem.toString());
            	    if(!iterageComHeroi.interageHeroi(hero, umaFase)) {
            	    	hero.voltaAUltimaPosicao();
            	    }
            	}
        }
    }
    
    public boolean ehPosicaoValida(ArrayList<Personagem> umaFase, Personagem p){
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
            if(!pIesimoPersonagem.isbTransponivel() && pIesimoPersonagem.getPosicao().igual(p.getPosicao()) && !p.equals(pIesimoPersonagem)) {
            	if(p instanceof Hero && pIesimoPersonagem instanceof IterageComHeroi) {
            		return true;
            	}
            	return false;
            }
        }        
        return true;
    }
}
