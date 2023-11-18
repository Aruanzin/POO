package Modelo;

import Auxiliar.Consts;
import Modelo.Personagem;
import interfaces.Monstro;

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

	public ArrayList<Personagem> ehPosicaoValida(Fase f, Posicao p) {
		ArrayList<Personagem> umaFase = f.getPersonagens();
		ArrayList<Personagem> personagensNaPosicao = new ArrayList<Personagem>();

		for (int i = 1; i < umaFase.size(); i++) {
			Personagem pIesimoPersonagem = umaFase.get(i);

			if (pIesimoPersonagem.getPosicao().igual(p) && !this.equals(pIesimoPersonagem)) {
				personagensNaPosicao.add(pIesimoPersonagem);
			}
		}

		return personagensNaPosicao;
	}

	public boolean podeAndar(ArrayList<Personagem> personagens) {
		for (Personagem p : personagens) {
			if (p.pPosicao.equivale(p.getPosicao()) && !p.bTransponivel) {
				return false;
			}
		}
		return true;
	}

	public boolean algumPersonagemTransponivel(ArrayList<Personagem> personagens) {
		for (Personagem pIesimoPersonagem : personagens) {
			if (pIesimoPersonagem.bTransponivel) {
				return true; // Se encontrar um personagem transponível, retorna true
			}
		}
		return false; // Se nenhum personagem transponível for encontrado, retorna false
	}

	public boolean algumPersonagemNaoTransponivel(ArrayList<Personagem> personagens) {
		for (Personagem pIesimoPersonagem : personagens) {
			if (!pIesimoPersonagem.bTransponivel) {
				return true; // Se encontrar um personagem transponível, retorna true
			}
		}
		return false; // Se nenhum personagem transponível for encontrado, retorna false
	}

	public ArrayList<Personagem> moveUp(int pixels) {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() - pixels, this.pPosicao.getColuna()));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveUp(pixels);
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveUp(pixels);
		}

		return personagemIteragido;
	}

	public ArrayList<Personagem> moveDown(int pixels) {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() + pixels, this.pPosicao.getColuna()));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveDown(pixels);
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveDown(pixels);
		}

		return personagemIteragido;
	}

	public ArrayList<Personagem> moveRight(int pixels) {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() + pixels));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveRight(pixels);
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveRight(pixels);
		}
		return personagemIteragido;
	}

	public ArrayList<Personagem> moveLeft(int pixels) {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() - pixels));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveLeft(pixels);
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveLeft(pixels);
		}
		return personagemIteragido;
	}

	public ArrayList<Personagem> moveUp() {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() - Consts.PIXELS, this.pPosicao.getColuna()));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveUp();
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveUp();
		}
		return personagemIteragido;
	}

	public ArrayList<Personagem> moveDown() {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha() + Consts.PIXELS, this.pPosicao.getColuna()));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveDown();
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveDown();
		}

		return personagemIteragido;
	}

	public ArrayList<Personagem> moveRight() {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() + Consts.PIXELS));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveRight();
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveRight();
		}
		return personagemIteragido;
	}

	public ArrayList<Personagem> moveLeft() {
		ArrayList<Personagem> personagemIteragido = ehPosicaoValida(Desenho.acessoATelaDoJogo().getFase(),
				new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() - Consts.PIXELS));
		if (personagemIteragido.size() == 0) {
			this.pPosicao.moveLeft();
		} else if (algumPersonagemTransponivel(personagemIteragido) && podeAndar(personagemIteragido)) {
			this.pPosicao.moveLeft();
		}
		return personagemIteragido;
	}

	public boolean temAlgumMonstro(ArrayList<Personagem> personagens) {
		for (Personagem pIesimoPersonagem : personagens) {
			if (pIesimoPersonagem instanceof Monstro) {
				return true; // Se encontrar um personagem transponível, retorna true
			}
		}
		return false;
	}
}
