package Controler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.Hero;
import Modelo.Personagem;
import interfaces.Monstro;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

	private static final long serialVersionUID = -286670056963397934L;
	private ArrayList<Fase> fases;
	private ControleDeJogo cj = new ControleDeJogo();
	private Graphics g2;
	private int atualFase = 0;

	public Tela() {
		Desenho.setCenario(this);
		initComponents();
		this.addMouseListener(this); /* mouse */

		this.addKeyListener(this); /* teclado */
		this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
				Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

		fases = new ArrayList<Fase>();
		for (int i = 1; i < 5; i++) {
			fases.add(lerNovaFase("fase" + i + ".txt"));
		}

	}

	public void passouDeFase() {
		atualFase++;
	}

	public void addPersonagem(Personagem umPersonagem) {
		fases.get(atualFase).addPersonagem(umPersonagem);
	}

	public void removePersonagem(Personagem umPersonagem) {
		fases.get(atualFase).removePersonagem(umPersonagem);
	}

	public Hero getHero() {
		return fases.get(atualFase).getHero();
	}

	public Graphics getGraphicsBuffer() {
		return g2;
	}

	public ArrayList<Personagem> getPersonagens() {
		return fases.get(atualFase).getPersonagens();
	}

	public Fase getFase() {
		return fases.get(atualFase);
	}

	public void paint(Graphics gOld) {
		Graphics g = this.getBufferStrategy().getDrawGraphics();
		Color fundo = new Color(72, 32, 6);
		/* Criamos um contexto gráfico */
		g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
		/************* Desenha cenário de fundo **************/
		g2.setColor(fundo); // Defina a cor do fundo
		g2.fillRect(0, 0, getWidth(), getHeight());

		if (!this.fases.get(atualFase).getPersonagens().isEmpty()) {
			this.cj.processaTudo(fases.get(atualFase));
			this.cj.desenhaTudo(fases.get(atualFase).getPersonagens());
		} else {
			System.out.println("FASE VAZIA");
		}

		g.dispose();
		g2.dispose();
		if (!getBufferStrategy().contentsLost()) {
			getBufferStrategy().show();
		}
	}

	public void go() {
		TimerTask task = new TimerTask() {
			public void run() {
				repaint();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, Consts.PERIOD);
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_C:
			fases.get(atualFase).getPersonagens().clear();
			break;
		case KeyEvent.VK_UP:
			fases.get(atualFase).getHero().moveUp();
			break;
		case KeyEvent.VK_DOWN:
			fases.get(atualFase).getHero().moveDown();
			break;
		case KeyEvent.VK_LEFT:
			fases.get(atualFase).getHero().moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			fases.get(atualFase).getHero().moveRight();
			break;
		case KeyEvent.VK_E:
		case KeyEvent.VK_SPACE:
			fases.get(atualFase).getHero().atirar();
			break;
		case KeyEvent.VK_R:
			fases.set(atualFase, lerNovaFase("fase" + (atualFase + 1) + ".txt"));
			break;
		default:
			// Handle other key codes if needed
			break;
		}

		this.setTitle("-> Cell: " + (fases.get(atualFase).getHero().getPosicao().getColuna()) + ", "
				+ (fases.get(atualFase).getHero().getPosicao().getLinha()));

		// repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
	}

	public void mousePressed(MouseEvent e) {
		/* Clique do mouse desligado */
		int x = e.getX();
		int y = e.getY();

		x = Math.round(x / 5) * 5;
		y = Math.round(y / 5) * 5;

		this.setTitle("X: " + x + ", Y: " + y + " -> Cell: " + y + ", " + x);

		fases.get(atualFase).getHero().getPosicao().setPosicao(y, x);

		repaint();
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("POO2023-1 - Skooter");
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setResizable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 561, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 500, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents
		// Variables declaration - do not modify//GEN-BEGIN:variables
		// End of variables declaration//GEN-END:variables

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public char[][] lerFaseDoArquivo(String arquivo) {
		try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
			ArrayList<char[]> linhas = new ArrayList<>();
			String linha;

			while ((linha = reader.readLine()) != null) {
				// Lê cada linha do arquivo e converte os caracteres em valores inteiros
				char[] valores = new char[linha.length()];
				for (int i = 0; i < linha.length(); i++) {
					valores[i] = linha.charAt(i);
				}
				linhas.add(valores);
			}

			// Converte a lista de linhas em uma matriz de inteiros
			char[][] faseData = new char[linhas.size()][];
			for (int i = 0; i < linhas.size(); i++) {
				faseData[i] = linhas.get(i);
			}

			return faseData;
		} catch (IOException e) {
			e.printStackTrace();
			return null; // Tratamento de erro
		}
	}

	private Fase lerNovaFase(String faseArquivo) {
		char[][] faseData = lerFaseDoArquivo(faseArquivo);
		// Crie os personagens com base nos valores lidos da fase
		Fase fase = new Fase();
		ArrayList<Personagem> personagens = new ArrayList<Personagem>();
		Personagem personagem = null;
		int posicaoX = 0, posicaoY = 0;
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				char valor = faseData[i][j];
				personagem = FabricaPersonagem.criarPersonagem(valor);
				if (personagem != null) {
					personagem.setPosicao(posicaoY, posicaoX);
					if (!(personagem instanceof Monstro)) {
						personagens.add(0, personagem);
					} else {
						personagens.add(personagem);
					}
				}
				posicaoX += Consts.CELL_SIDE;
			}
			posicaoX = 0;
			posicaoY += Consts.CELL_SIDE;
		}
		for (Personagem umPersonagem : personagens) {
			if (umPersonagem instanceof Hero) {
				personagens.remove(umPersonagem); // Remove o herói da posição atual
				personagens.add(0, umPersonagem); // Adiciona o herói no início da lista
				fase.setHero((Hero) umPersonagem);
				break;
			}

		}
		fase.setPersonagens(personagens);
		return fase;
	}
}
