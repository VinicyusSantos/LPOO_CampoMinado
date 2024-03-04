package game.campominado.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import game.campominado.celula.Bomba;
import game.campominado.celula.Celula;
import game.campominado.celula.EspacoVazio;
import game.campominado.jogador.Jogador;
import game.campominado.tabuleiro.Tabuleiro;
import game.campominado.tabuleiro.TabuleiroIniciante;

public class TabuleiroModoClassicoMulti extends JFrame {
    private static final long serialVersionUID = 1L;

    private ImageIcon dinamite;
    private JButton[][] buttons;
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private JLabel jogadorLabel;

    private boolean turnoJogador1 = true;

    public TabuleiroModoClassicoMulti(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.dinamite = new ImageIcon("src/game/campominado/imagens/Dinamite.png");
        
        // Solicitar nome dos jogadores
        String nomeJogador1 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 1:");
        String nomeJogador2 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 2:");
        
        jogador1 = new Jogador(nomeJogador1, 0);
        jogador2 = new Jogador(nomeJogador2, 0);

        setTitle("Modo Clássico - Campo Minado (Multiplayer)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550); 
        setLayout(new BorderLayout()); 
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        
        jogadorLabel = new JLabel("Jogador 1: " + jogador1.getName() + " - Pontuação: " + jogador1.getXp());
        jogadorLabel.setForeground(Color.WHITE);
        jogadorLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        jogadorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(jogadorLabel, BorderLayout.NORTH);

        JPanel tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
        tabuleiroPanel.setBackground(Color.BLACK);
        
        buttons = new JButton[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                JButton button = new JButton();
                button.setFocusable(false);
                button.setFont(new Font("Verdana", Font.BOLD, 20));
                button.setBackground(new Color(220, 220, 220));
                button.setForeground(new Color(176,224,230));
                button.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));
                button.setLayout(null);

                buttons[i][j] = button;
                tabuleiroPanel.add(button);

                final int linha = i;
                final int coluna = j;

                button.addActionListener(new ActionListener() {
                	@Override
                	public void actionPerformed(ActionEvent e) {
                	    Celula celula = tabuleiro.getCelulas()[linha][coluna];
                	    if (!celula.isAberto() && !celula.isBandeira()) {
                	        if (celula instanceof Bomba) {
                	            showBomb(linha, coluna);
                	            endGame(false);
                	        } else if (celula instanceof EspacoVazio) {
                	            revealEmptyCells(linha, coluna);
                	            if (turnoJogador1) {
                	                jogador1.setXp(jogador1.getXp() + 1);
                	                jogadorLabel.setText("Jogador 1: " + jogador1.getName() + " - Pontuação: " + jogador1.getXp());
                	            } else {
                	                jogador2.setXp(jogador2.getXp() + 1);
                	                jogadorLabel.setText("Jogador 2: " + jogador2.getName() + " - Pontuação: " + jogador2.getXp());
                	            }
                	            checkGameStatus();
                	            turnoJogador1 = !turnoJogador1; // Alternar turno dos jogadores
                	        } else {
                	            revealCell(linha, coluna, celula.getNumeroBombasVizinhas());
                	            if (turnoJogador1) {
                	                jogador1.setXp(jogador1.getXp() + 1);
                	                jogadorLabel.setText("Jogador 1: " + jogador1.getName() + " - Pontuação: " + jogador1.getXp());
                	            } else {
                	                jogador2.setXp(jogador2.getXp() + 1);
                	                jogadorLabel.setText("Jogador 2: " + jogador2.getName() + " - Pontuação: " + jogador2.getXp());
                	            }
                	            checkGameStatus();
                	            turnoJogador1 = !turnoJogador1; // Alternar turno dos jogadores
                	        }
                	    } else if (!celula.isAberto() && celula.isBandeira()) {
                	        removeFlag(button);
                	    }
                	}
                });
                
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            JButton clickedButton = (JButton) e.getSource();
                            if (!hasFlag(clickedButton)) {
                                implantFlag(clickedButton);
                            } else {
                                removeFlag(clickedButton);
                            }
                        }
                    }
                });
            }
        }
        add(tabuleiroPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    private boolean hasFlag(JButton button) {
        return button.getIcon() != null;
    }

    private void implantFlag(JButton button) {
        ImageIcon flagIcon = new ImageIcon("src/game/campominado/imagens/bandeira.png");
        Image scaledFlag = flagIcon.getImage().getScaledInstance(button.getWidth() - 10, button.getHeight() - 10, Image.SCALE_SMOOTH);
        ImageIcon scaledFlagIcon = new ImageIcon(scaledFlag);
        button.setIcon(scaledFlagIcon);
        
        int linha = getButtonRow(button);
        int coluna = getButtonColumn(button);
        tabuleiro.getCelulas()[linha][coluna].setBandeira(true);
    }

    private void removeFlag(JButton button) {
    	button.setIcon(null);
        
        int linha = getButtonRow(button);
        int coluna = getButtonColumn(button);
        tabuleiro.getCelulas()[linha][coluna].setBandeira(false);
    }
    
    private int getButtonRow(JButton button) {
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                if (buttons[i][j] == button) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getButtonColumn(JButton button) {
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                if (buttons[i][j] == button) {
                    return j;
                }
            }
        }
        return -1;
    }

    private void showBomb(int linha, int coluna) {
        JButton button = buttons[linha][coluna];
        button.setIcon(new ImageIcon(dinamite.getImage().getScaledInstance(button.getWidth() - 10, button.getHeight() - 10, Image.SCALE_SMOOTH)));
        button.setBackground(Color.RED);
    }

    private void revealEmptyCells(int linha, int coluna) {
        boolean[][] visited = new boolean[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        revealEmptyCellsRecursive(linha, coluna, visited);
    }
    
    private void revealEmptyCellsRecursive(int linha, int coluna, boolean[][] visited) {
        if (linha < 0 || linha >= tabuleiro.getLinhas() || coluna < 0 || coluna >= tabuleiro.getColunas() || visited[linha][coluna]) {
            return;
        }

        visited[linha][coluna] = true;

        Celula celula = tabuleiro.getCelulas()[linha][coluna];

        if (celula instanceof EspacoVazio && !celula.isAberto()) {
            int numeroBombasVizinhas = celula.numeroBombasVizinhas();
            revealCell(linha, coluna, numeroBombasVizinhas);
            if (numeroBombasVizinhas == 0) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int newLinha = linha + i;
                        int newColuna = coluna + j;
                        if (!(i == 0 && j == 0)) {
                            revealEmptyCellsRecursive(newLinha, newColuna, visited);
                        }
                    }
                }
            }
        } else if (!celula.isAberto()) {
            revealCell(linha, coluna, celula.numeroBombasVizinhas());
        }
    }

    private void revealCell(int linha, int coluna, int valor) {
        JButton button = buttons[linha][coluna];
        button.setText(String.valueOf(valor));
        button.setBackground(Color.GRAY);
    }

    private void checkGameStatus() {
        // logica para verificar de ganhou ou perdeu
    }

    private void endGame(boolean win) {
    	if (!win) {
            // Se o jogador perdeu, mostra todas as bombas
            showAllBombs();
        }
    	
        String message;
        String title;
        int optionType;

        if (win) {
            message = "Parabéns! Você ganhou!";
            title = "Fim de Jogo - Vitória";
            optionType = JOptionPane.INFORMATION_MESSAGE;
        } else {
            message = "Você perdeu! Tente novamente.";
            title = "Fim de Jogo - Derrota";
            optionType = JOptionPane.ERROR_MESSAGE;
        }

        JOptionPane.showMessageDialog(this, message, title, optionType);

        dispose(); // Fecha a janela atual
        new JogoFrame(); // Volta ao menu principal
    }
    
    private void showAllBombs() {
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                Celula celula = tabuleiro.getCelulas()[i][j];
                if (celula instanceof Bomba) {
                    showBomb(i, j);
                }
            }
        }
    }
    
    @Override
    public void dispose() {
        super.dispose();
        try {
            jogador1.salvarInfo("src/game/campominado/jogador/info_jogador.txt");
            jogador2.salvarInfo("src/game/campominado/jogador/info_jogador.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Não foi possível salvar as informações dos jogadores.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
