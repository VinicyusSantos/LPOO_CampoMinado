package game.campominado.tabuleiro;

import game.campominado.celula.BombaMaluca;
import game.campominado.celula.Celula;
import game.campominado.celula.EspacoVazio;

public class TabuleiroMaluco extends Tabuleiro {
    
    // Construtor
    public TabuleiroMaluco(int linhas, int colunas, int bombas) {
        super(linhas, colunas, bombas);
    }

    // Método para inicializar o tabuleiro
    
    @Override
    public void iniciarCampo() {
        Celula[][] celulas = getCelulas();
        celulas = new Celula[getLinhas()][getColunas()];
        setCelulas(celulas);  
        for (int i = 0; i < getLinhas(); i++) {
            for (int j = 0; j < getColunas(); j++) {
                celulas[i][j] = new EspacoVazio();
            }
        }
        
        // Adicionando vizinhos
        for (int i = 0; i < getLinhas(); i++) {
            for (int j = 0; j < getColunas(); j++) {
                if (i > 0) {
                    if (j > 0)
                        celulas[i][j].addVizinhos(celulas[i - 1][j - 1]);
                    celulas[i][j].addVizinhos(celulas[i - 1][j]);
                    if (j < getColunas() - 1)
                        celulas[i][j].addVizinhos(celulas[i - 1][j + 1]);
                }

                if (j > 0)
                    celulas[i][j].addVizinhos(celulas[i][j - 1]);
                if (j < getColunas() - 1)
                    celulas[i][j].addVizinhos(celulas[i][j + 1]);

                if (i < getLinhas() - 1) {
                    if (j > 0)
                        celulas[i][j].addVizinhos(celulas[i + 1][j - 1]);
                    celulas[i][j].addVizinhos(celulas[i + 1][j]);
                    if (j < getColunas() - 1)
                        celulas[i][j].addVizinhos(celulas[i + 1][j + 1]);
                }
            }
        }
        addBombaMaluca(); // Adicionando a bomba maluca
    }

    // Método para adicionar a bomba maluca ao tabuleiro
    public void addBombaMaluca() {
        Celula[][] celulas = getCelulas();
        int bombaMalucaLinhas = getLinhas() / 2;
        int bombaMalucaColunas = getColunas() / 2;
        celulas[bombaMalucaLinhas][bombaMalucaColunas] = new BombaMaluca();
    }
}
