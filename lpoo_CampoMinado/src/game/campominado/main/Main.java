package game.campominado.main;

import game.campominado.tabuleiro.GeradorTabuleiro;
import game.campominado.tabuleiro.Tabuleiro;

public class Main {

	public static void main(String[] args) {
		
		int linhas = 9; 
        int colunas = 9; 
        int bombas = 10; 
		
		
		GeradorTabuleiro n = new GeradorTabuleiro(linhas,colunas,bombas);
		Tabuleiro tab = n.gerarTabuleiro();
		System.out.println(tab);
	}

}
