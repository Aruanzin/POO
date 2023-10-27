package Controler;

import Modelo.Personagem;
import Modelo.Hero;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Posicao;

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

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private static final long serialVersionUID = -286670056963397934L;
	private Hero hero;
    private ArrayList<Personagem> faseAtual;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;

    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        faseAtual = new ArrayList<Personagem>();

//        /*Cria faseAtual adiciona personagens*/
//        hero = new Hero("skoot.png");
//        hero.setPosicao(0, 7);
//        this.addPersonagem(hero);
//        
//        ZigueZague zz = new ZigueZague("robo.png");
//        zz.setPosicao(5, 5);
//        this.addPersonagem(zz);
//
//        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("roboPink.png");
//        bBichinhoH.setPosicao(3, 3);
//        this.addPersonagem(bBichinhoH);
//
//        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("roboPink.png");
//        bBichinhoH2.setPosicao(6, 6);
//        this.addPersonagem(bBichinhoH2);
//
//        Obstaculo bV = new Obstaculo("caveira.png");
//        bV.setPosicao(9, 1);
//        this.addPersonagem(bV);
//        
//        int[][] faseData = {
//            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 ,1},
//            {1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
//            {1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
//            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 ,1},
//            {1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
//            {1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
//            {1, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0 ,1},
//            {1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
//            {1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
//            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 ,1},
//            {1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
//            {1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
//        };
//
//        try (PrintWriter writer = new PrintWriter("fase1.txt")) {
//            for (int[] row : faseData) {
//                for (int value : row) {
//                    writer.print(value);
//                }
//                writer.println(); // Próxima linha
//            }
//        } catch (FileNotFoundException e) {		
//			e.printStackTrace();
//		}
     // Carregue a fase a partir do arquivo
        int[][] faseData = lerFaseDoArquivo("fase1.txt");
//        System.out.println(faseData.toString());
        // Crie os personagens com base nos valores lidos da fase
        for (int i = 0; i < faseData.length; i++) {
            for (int j = 0; j < faseData[i].length; j++) {
                int valor = faseData[i][j];
                Personagem personagem = FabricaPersonagem.criarPersonagem(valor);
                if (personagem != null) {
                    personagem.setPosicao(i, j);
                    addPersonagem(personagem);
                }
            }
        }
        
        if (hero == null) {
            for (Personagem personagem : faseAtual) {
                if (personagem instanceof Hero) {
                	faseAtual.remove(personagem); // Remove o herói da posição atual
                    faseAtual.add(0, personagem); // Adiciona o herói no início da lista
                	hero = (Hero) personagem;
                    break;
                }
            }
        }
    }

    public boolean ehPosicaoValida(Posicao p){
        return cj.ehPosicaoValida(this.faseAtual, p);
    }
    public void addPersonagem(Personagem umPersonagem) {
        faseAtual.add(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        faseAtual.remove(umPersonagem);
    }

    public Graphics getGraphicsBuffer(){
        return g2;
    }
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        Color fundo = new Color(72,32,6);
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /*************Desenha cenário de fundo**************/
        g2.setColor(fundo); // Defina a cor do fundo
        g2.fillRect(0, 0, getWidth(), getHeight()); 
        //        for (int i = 0; i < Consts.RES; i++) {
//            for (int j = 0; j < Consts.RES; j++) {
//                try {
//                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
//                    g2.drawImage(newImage,
//                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
//
//                } catch (IOException ex) {
//                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
        if (!this.faseAtual.isEmpty()) {
            this.cj.desenhaTudo(faseAtual);
            this.cj.processaTudo(faseAtual);
        }else{
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
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.faseAtual.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.moveRight();
        }

        this.setTitle("-> Cell: " + (hero.getPosicao().getColuna()) + ", "
                + (hero.getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado*/
         int x = e.getX();
         int y = e.getY();
     
         this.setTitle("X: "+ x + ", Y: " + y +
         " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
        
         this.hero.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         
        repaint();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2023-1 - Skooter");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

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

    public int[][] lerFaseDoArquivo(String arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            ArrayList<int[]> linhas = new ArrayList<>();
            String linha;

            while ((linha = reader.readLine()) != null) {
                // Lê cada linha do arquivo e converte os caracteres em valores inteiros
                int[] valores = new int[linha.length()];
                for (int i = 0; i < linha.length(); i++) {
                    valores[i] = Character.getNumericValue(linha.charAt(i));
                }
                linhas.add(valores);
            }

            // Converte a lista de linhas em uma matriz de inteiros
            int[][] faseData = new int[linhas.size()][];
            for (int i = 0; i < linhas.size(); i++) {
                faseData[i] = linhas.get(i);
            }

            return faseData;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Tratamento de erro
        }
    }


}