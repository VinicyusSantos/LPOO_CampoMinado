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
import game.campominado.jogador.VisualizarInformacoes;
import game.campominado.tabuleiro.Tabuleiro;

public class TabuleiroModoClassicoSingle extends JFrame {
    private static final long serialVersionUID = 1L;

    private ImageIcon dinamite;
    private JButton[][] buttons;
    private Tabuleiro tabuleiro;
    private Jogador jogador;
    private JLabel jogadorLabel;

    public TabuleiroModoClassicoSingle(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.dinamite = new ImageIcon("src/game/campominado/imagens/Dinamite.png");
        
        String nome = JOptionPane.showInputDialog(this, "Digite seu nome:");
        jogador = new Jogador(nome, 0);

        setTitle("Modo Clássico - Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550); 
        setLayout(new BorderLayout()); 
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        
        jogadorLabel = new JLabel("Jogador: " + jogador.getName() + " - Pontuação: " + jogador.getXp());
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
                                jogador.setXp(jogador.getXp() + 1);
                                jogadorLabel.setText("Jogador: " + jogador.getName() + " - Pontuação: " + jogador.getXp());
                                checkGameStatus();
                            } else {
                                revealCell(linha, coluna, celula.getNumeroBombasVizinhas());
                                jogador.setXp(jogador.getXp() + 1);
                                jogadorLabel.setText("Jogador: " + jogador.getName() + " - Pontuação: " + jogador.getXp());
                                checkGameStatus();
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
                checkGameStatus(); 
            }
        } else if (!celula.isAberto()) {
            revealCell(linha, coluna, celula.numeroBombasVizinhas());
        }
    }

    private void revealCell(int linha, int coluna, int valor) {
        JButton button = buttons[linha][coluna];
        button.setText(String.valueOf(valor));
        button.setBackground(Color.GRAY);
        checkGameStatus(); 
    }
    
    private boolean allBombsFlagged() {
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                Celula celula = tabuleiro.getCelulas()[i][j];
                if (celula instanceof Bomba) {
                    if (!celula.isBandeira()) {
                        return false; // Se uma bomba não estiver marcada, retorna falso
                    }
                }
            }
        }
        return true; // Se todas as bombas estiverem marcadas, retorna verdadeiro
    }

    private void checkGameStatus() {
        // Verifica se o jogo terminou (venceu ou perdeu)
        // Se sim, chama o método endGame
    	if(allBombsFlagged()) {
    		endGame(true);
    	}
 
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
            jogador.salvarInfo("src/game/campominado/jogador/info_jogador.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Não foi possível salvar as informações do jogador.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
