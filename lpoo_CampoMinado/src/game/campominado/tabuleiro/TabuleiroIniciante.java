package game.campominado.tabuleiro;

import game.campominado.celula.Celula;
import game.campominado.celula.EspacoVazio;

public class TabuleiroIniciante extends Tabuleiro {

    public TabuleiroIniciante(int linhas, int colunas, int bombas) {
        super(linhas, colunas, bombas);
        iniciarCampo();
    }

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
        // adicionando vizinhos para verificar a qts de bombas
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
                if (j < getColunas() - 1) // Corrigido aqui
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
        addMinas();
    }
}
