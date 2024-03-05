package game.campominado.tabuleiro;

import game.campominado.celula.Celula;
import game.campominado.exception.ValorInvalidoException;

public class GeradorTabuleiro {
    
    // Definição de atributos
    private int linhas;
    private int colunas;
    private int bombas;
    private int dificuldade;
    
    // Definição de construtor
    public GeradorTabuleiro(int linhas, int colunas, int bombas, int dificuldade) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.bombas = bombas;
        this.dificuldade = dificuldade;
    }

    // Método para gerar tabuleiro de acordo com a dificuldade
    public Tabuleiro gerarTabuleiro() {
        if (dificuldade == 1) {
            TabuleiroIniciante tab = new TabuleiroIniciante(linhas, colunas, bombas);
            tab.iniciarCampo();
            return tab;
        } else if (dificuldade == 2) {
            TabuleiroIntermediario tab = new TabuleiroIntermediario(linhas, colunas, bombas);
            tab.iniciarCampo();
            return tab;
        } else if (dificuldade == 3) {
            TabuleiroAvancado tab = new TabuleiroAvancado(linhas, colunas, bombas);
            tab.iniciarCampo();
            return tab;
        } else if (dificuldade == 4) {
            // Tabuleiro com bomba maluca
            TabuleiroMaluco tab = new TabuleiroMaluco(linhas, colunas, bombas);
            tab.iniciarCampo();
            return tab;
        } else {
            throw new ValorInvalidoException("Dificuldade inválida.");
        }
    }

}
