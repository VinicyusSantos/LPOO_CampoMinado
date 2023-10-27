package game.campominado.campo;

import game.campominado.tabuleiro.Tabuleiro;

public class GeradorTabuleiro {
	private int linhas = 9;
	private int colunas = 9;
	private int bombas = 9;
	private Tabuleiro tab;
	
	public Tabuleiro gerarTabuleiro() {
		tab = new Tabuleiro(linhas, colunas, bombas);
        tab.iniciarCampo();
        return tab;
    }
	
}
