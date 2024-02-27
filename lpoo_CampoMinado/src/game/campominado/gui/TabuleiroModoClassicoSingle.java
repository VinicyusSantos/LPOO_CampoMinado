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

public class TabuleiroModoClassicoSingle extends JFrame {
    private static final long serialVersionUID = 1L;

    //atributos
    private ImageIcon dinamite;
    private JButton[][] buttons;
    private Tabuleiro tabuleiro;
    private Jogador jogador;
    private JLabel jogadorLabel;

    public TabuleiroModoClassicoSingle(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.dinamite = new ImageIcon("src/game/campominado/imagens/Dinamite.png");
        
     // Solicitar nome do jogador
        String nome = JOptionPane.showInputDialog(this, "Digite seu nome:");
        jogador = new Jogador(nome,0);

        //titulo e janela do jogo
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
        add(jogadorLabel, BorderLayout.NORTH); //topo da tela

        JPanel tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
        tabuleiroPanel.setBackground(Color.BLACK);
        

        //adicionando os botões e suas funcionalidades
        buttons = new JButton[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                JButton button = new JButton();
                button.setFocusable(false);
                button.setFont(new Font("Verdana", Font.BOLD, 20));
                button.setBackground(new Color(0, 0, 40));
                button.setForeground(new Color(0, 0, 250));
                button.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));
                button.setLayout(null); // Layout nulo para adicionar os ícones manualmente

                buttons[i][j] = button;
                tabuleiroPanel.add(button);

                final int linha = i;
                final int coluna = j;

                //verifica cada botão clicado, se tem bomba, bandeira, espaço vazio.
                button.addActionListener(new ActionListener() {
                    @Override
                	public void actionPerformed(ActionEvent e) {
                	    Celula celula = tabuleiro.getCelulas()[linha][coluna];
                	    if (!celula.isAberto() && !celula.isBandeira()) { // Verifica se a célula não foi aberta e não possui bandeira
                	        if (celula instanceof Bomba) {
                	            showBomb(linha, coluna);
                	            endGame(false);
                	        } else if (celula instanceof EspacoVazio) {
                	            revealEmptyCells(linha, coluna);
                	            // Incrementa XP do jogador ao clicar em uma célula sem bomba
                	            jogador.setXp(jogador.getXp() + 1);
                	            jogadorLabel.setText("Jogador: " + jogador.getName() + " - Pontuação: " + jogador.getXp());
                	            checkGameStatus();
                	        } else {
                	            revealCell(linha, coluna, celula.getNumeroBombasVizinhas());
                	            // Incrementa XP do jogador ao clicar em uma célula sem bomba
                	            jogador.setXp(jogador.getXp() + 1);
                	            jogadorLabel.setText("Jogador: " + jogador.getName() + " - Pontuação: " + jogador.getXp());
                	            checkGameStatus();
                	        }
                	    } else if (!celula.isAberto() && celula.isBandeira()) { // Se a célula possui bandeira
                	        removeFlag(button); // Remove a bandeira
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
        // Verifica se o botão já possui uma bandeira
        return button.getIcon() != null;
    }

    private void implantFlag(JButton button) {
        // Implanta uma bandeira no botão
        ImageIcon flagIcon = new ImageIcon("src/game/campominado/imagens/bandeira.png");
        Image scaledFlag = flagIcon.getImage().getScaledInstance(button.getWidth() - 10, button.getHeight() - 10, Image.SCALE_SMOOTH);
        ImageIcon scaledFlagIcon = new ImageIcon(scaledFlag);
        button.setIcon(scaledFlagIcon);
        
        // Define uma propriedade na célula indicando que uma bandeira foi colocada ali
        int linha = getButtonRow(button);
        int coluna = getButtonColumn(button);
        tabuleiro.getCelulas()[linha][coluna].setBandeira(true);
    }

    private void removeFlag(JButton button) {
    	button.setIcon(null);
        
        // Remove a propriedade na célula indicando que uma bandeira foi removida
        int linha = getButtonRow(button);
        int coluna = getButtonColumn(button);
        tabuleiro.getCelulas()[linha][coluna].setBandeira(false);
    }
    
    private int getButtonRow(JButton button) {
        // Obtém a linha do botão no tabuleiro
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                if (buttons[i][j] == button) {
                    return i;
                }
            }
        }
        return -1; // Retorna -1 se o botão não for encontrado
    }

    private int getButtonColumn(JButton button) {
        // Obtém a coluna do botão no tabuleiro
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                if (buttons[i][j] == button) {
                    return j;
                }
            }
        }
        return -1; // Retorna -1 se o botão não for encontrado
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
            return; // Se estiver fora do tabuleiro ou já foi visitada, retorna
        }

        visited[linha][coluna] = true;

        Celula celula = tabuleiro.getCelulas()[linha][coluna];

        // Se a célula atual for vazia e não estiver aberta, revela a célula e as vizinhas não vazias recursivamente
        if (celula instanceof EspacoVazio && !celula.isAberto()) {
            int numeroBombasVizinhas = celula.numeroBombasVizinhas(); // Obtém o número de bombas vizinhas
            revealCell(linha, coluna, numeroBombasVizinhas); // Mostra a célula vazia com as bombas vizinhas
            if (numeroBombasVizinhas == 0) { // Se não tiver bombas vizinhas, revela as células vizinhas
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int newLinha = linha + i;
                        int newColuna = coluna + j;
                        if (!(i == 0 && j == 0)) { // Não chama a função para a própria célula
                            revealEmptyCellsRecursive(newLinha, newColuna, visited);
                        }
                    }
                }
            }
        } else if (!celula.isAberto()) { // Se a célula não for vazia e ainda não estiver aberta, revela apenas ela
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
        if (win) {
            JOptionPane.showMessageDialog(this, "Parabéns! Você ganhou!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Você perdeu! Tente novamente.", "Fim de Jogo", JOptionPane.ERROR_MESSAGE);
        }
        // Implemente a lógica para reiniciar o jogo ou voltar ao menu principal
    }

    
}