package Controler;

import java.util.ArrayList;
import java.util.Iterator;

import Modelo.Bau;
import Modelo.Hero;
import Modelo.Personagem;
import Modelo.Porta;
import Modelo.Vida;
import interfaces.Monstro;

public class Fase {
	private Hero hero;
	private ArrayList<Personagem> faseAtual;
	private int numeroVidas;
	
	public Fase() {
		faseAtual = new ArrayList<Personagem>();
	}
	public void setPersonagens(ArrayList<Personagem> f) {
		faseAtual = f;
		for(Personagem personagem: faseAtual) {
			if(personagem instanceof Vida)
				numeroVidas++;
		}
		System.out.println(numeroVidas);
	}
	
	public ArrayList<Personagem> getPersonagens(){
		return faseAtual;
	};
	
	public void addPersonagem(Personagem umPersonagem) {
		faseAtual.add(umPersonagem);
		if(umPersonagem instanceof Vida)
			setNumeroVidas(getNumeroVidas() + 1);
	}
	public void addPersonagem(int posicao, Personagem umPersonagem) {
		faseAtual.add(posicao,umPersonagem);
	}
	public void removePersonagem(Personagem umPersonagem) {
		faseAtual.remove(umPersonagem);
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	public int getNumeroVidas() {
		return numeroVidas;
	}
	public void setNumeroVidas(int numeroVidas) {
		System.out.println("Vidas" + this.numeroVidas);
		this.numeroVidas = numeroVidas;
		if(this.numeroVidas == 0)
			jogoEncerrado();
	}
	public void jogoEncerrado() {
	    System.out.println("JOGO ENCERRADO" + this.numeroVidas);
	    Iterator<Personagem> iterator = faseAtual.iterator();
	    while (iterator.hasNext()) {
	        Personagem personagem = iterator.next();
	        if (personagem instanceof Monstro) {
	            ((Monstro) personagem).acabouAsVidas();  // Safely remove the element using the iterator
	        }else if(personagem instanceof Bau) {
	        	((Bau) personagem).setDesbloqueado();
	        }
	    }
	}
	    
    public void fimFase() {
    	faseAtual.removeIf(e -> e instanceof Monstro);
    	for (Personagem elemento : faseAtual) {
            if (elemento instanceof Porta) {
                Porta subclasseElemento = (Porta) elemento;
                subclasseElemento.abrirPorta();
            }
        }
    }
	    
}


