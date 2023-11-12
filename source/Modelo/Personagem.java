package Modelo;

import Auxiliar.Consts;
import Modelo.Personagem;
import java.util.ArrayList;
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

	public Personagem ehPosicaoValida(Fase f, Posicao p) {
		ArrayList<Personagem> umaFase = f.getPersonagens();
		Personagem pIesimoPersonagem;
		Personagem personagemIteragido = null;
		for (int i = 1; i < umaFase.size(); i++) {
			pIesimoPersonagem = umaFase.get(i);
			if (pIesimoPersonagem.getPosicao().igual(p) && !this.equals(pIesimoPersonagem)) {
				personagemIteragido = pIesimoPersonagem;
			}
		}
		return personagemIteragido;
	}

	public Personagem moveUp(int pixels) {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() - pixels, this.pPosicao.getColuna()));
		if(personagemIteragido == null ) {
			this.pPosicao.moveUp(pixels);
		} else if (personagemIteragido.bTransponivel) {
			this.pPosicao.moveUp(pixels);
		}
		return personagemIteragido;
	}

	public Personagem moveDown(int pixels) {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() + pixels, this.pPosicao.getColuna()));
		if(personagemIteragido == null ) {
			this.pPosicao.moveDown(pixels);
		} else if (personagemIteragido.bTransponivel ) {
			this.pPosicao.moveDown(pixels);
		}

		return personagemIteragido;
	}

	public Personagem moveRight(int pixels) {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() + pixels));
		if(personagemIteragido == null ) {
			this.pPosicao.moveRight(pixels);
		} else if (personagemIteragido.bTransponivel) {
			this.pPosicao.moveRight(pixels);
		}
		return personagemIteragido;
	}

	public Personagem moveLeft(int pixels) {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() - pixels));
		if(personagemIteragido == null ) {
			this.pPosicao.moveLeft(pixels);
		} else if (personagemIteragido.bTransponivel) {
			this.pPosicao.moveLeft(pixels);
		}
		return personagemIteragido;
	}
	public Personagem moveUp() {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() - Consts.PIXELS, this.pPosicao.getColuna()));
		if(personagemIteragido == null ) {
			this.pPosicao.moveUp();
		} else if (personagemIteragido.bTransponivel) {
			this.pPosicao.moveUp();
		}
		return personagemIteragido;
	}

	public Personagem moveDown() {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() + Consts.PIXELS, this.pPosicao.getColuna()));
		if(personagemIteragido == null ) {
			this.pPosicao.moveDown();
		} else if (personagemIteragido.bTransponivel ) {
			this.pPosicao.moveDown();
		}

		return personagemIteragido;
	}

	public Personagem moveRight() {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() + Consts.PIXELS));
		if(personagemIteragido == null ) {
			this.pPosicao.moveRight();
		} else if (personagemIteragido.bTransponivel) {
			this.pPosicao.moveRight();
		}
		return personagemIteragido;
	}

	public Personagem moveLeft() {
		Personagem personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() - Consts.PIXELS));
		if(personagemIteragido == null ) {
			this.pPosicao.moveLeft();
		} else if (personagemIteragido.bTransponivel) {
			this.pPosicao.moveLeft();
		}
		return personagemIteragido;
	}
}
