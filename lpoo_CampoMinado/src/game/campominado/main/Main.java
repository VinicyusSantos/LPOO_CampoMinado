package game.campominado.main;

import game.campominado.tabuleiro.GeradorTabuleiro;

public class Main {

	public static void main(String[] args) {
		
		int linhas = 9; 
        int colunas = 9; 
        int bombas = 10; 
		
		
		GeradorTabuleiro n = new GeradorTabuleiro(linhas,colunas,bombas);
		n.gerarTabuleiro();
		System.out.println(n);
	}

}
