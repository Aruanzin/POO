package Modelo;

import Auxiliar.Consts;
import Modelo.Personagem;
import java.util.ArrayList;
import interfaces.IterageComHeroi;
import Auxiliar.Desenho;
import Auxiliar.Posicao;
import Controler.Fase;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

public abstract class Personagem implements Serializable {

	private static final long serialVersionUID = 9100617890982042031L;
	protected ImageIcon iImage;
	protected Posicao pPosicao;
	protected boolean bTransponivel; /* Pode passar por cima? */
	protected boolean bMortal; /* Se encostar, morre? */
	private Image img;
	private int spriteWidth = 16;
	private int spriteHeight = 16;

	protected Personagem(int spriteX, int spriteY) {
		criaPersonagem(spriteX, spriteY);
	}
	protected Personagem(int spriteX, int spriteY, int spriteW, int spriteH) {
		spriteWidth = spriteW;
		spriteHeight = spriteH;		
		criaPersonagem(spriteX, spriteY);
	}
	private void criaPersonagem(int spriteX, int spriteY) {
		this.pPosicao = new Posicao(1, 1);
		this.bTransponivel = true;
		this.bMortal = false;
		try {
			String imagePath = new java.io.File(".").getCanonicalPath() + Consts.PATH + "sprites.png";
			iImage = new ImageIcon(imagePath);
			img = iImage.getImage();
			setImage(spriteX, spriteY);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void setImage(int spriteX, int spriteY) {
		BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, spriteX * 16, spriteY * 16,
				spriteX * 16 + spriteWidth, spriteY * 16 + spriteHeight, null);
		iImage = new ImageIcon(bi);
	}

	public Posicao getPosicao() {
		return pPosicao;
	}

	public Posicao getPosicaoAnterior() {
		return new Posicao(this.pPosicao.getLinhaAnterior(), this.pPosicao.getColunaAnterior());
	}

	public boolean isbTransponivel() {
		return bTransponivel;
	}

	public void setbTransponivel(boolean bTransponivel) {
		this.bTransponivel = bTransponivel;
	}

	public void autoDesenho() {
		Desenho.desenhar(this.iImage, this.pPosicao.getColuna(), this.pPosicao.getLinha());
	}

	public boolean setPosicao(int linha, int coluna) {
		return pPosicao.setPosicao(linha, coluna);
	}

	public boolean setPosicao(Posicao p) {
		return setPosicao(p.getLinha(), p.getColuna());
	}

	public void voltaAUltimaPosicao() {
		this.pPosicao.volta();
	}

	public boolean ehPosicaoValida(Fase f, Posicao p) {
		ArrayList<Personagem> umaFase = f.getPersonagens();
        Hero hero = (Hero)umaFase.get(0);
		Personagem pIesimoPersonagem;
		boolean podeAndar = true;
		for (int i = 1; i < umaFase.size(); i++) {
			pIesimoPersonagem = umaFase.get(i);
			if (!pIesimoPersonagem.isbTransponivel() && pIesimoPersonagem.getPosicao().igual(p)
					&& !this.equals(pIesimoPersonagem)) {
				if (this instanceof Hero && pIesimoPersonagem instanceof IterageComHeroi) {
					 IterageComHeroi iterageComHeroi = (IterageComHeroi) pIesimoPersonagem;
            	    System.out.println(pIesimoPersonagem.toString());
            	    if(!iterageComHeroi.interageHeroi(hero, f));
            	    	podeAndar = false;
				}else {
					podeAndar = false;
				}
			}
		}
		return podeAndar;
	}

	public boolean moveUp() {
		if (!ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() - Consts.PIXELS, this.pPosicao.getColuna()))) {
			return false;
		}

		return this.pPosicao.moveUp();
	}

	public boolean moveDown() {
		if (!ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() + Consts.PIXELS, this.pPosicao.getColuna()))) {
			return false;
		}
		return this.pPosicao.moveDown();
	}

	public boolean moveRight() {
		if (!ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() + Consts.PIXELS))) {
			return false;
		}
		return this.pPosicao.moveRight();
	}

	public boolean moveLeft() {
		if (!ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() - Consts.PIXELS))) {
			return false;
		}
		return this.pPosicao.moveLeft();
	}
}
