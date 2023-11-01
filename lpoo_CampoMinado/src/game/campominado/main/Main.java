package game.campominado.main;

import game.campominado.tabuleiro.GeradorTabuleiro;
import java.util.Scanner;
import game.campominado.tabuleiro.Tabuleiro;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int linhas = 9;
		int colunas = 9;
		int bombas = 5;

		GeradorTabuleiro n = new GeradorTabuleiro(linhas, colunas, bombas);
		Tabuleiro tab = n.gerarTabuleiro();
		System.out.println(tab);

		System.out.print("linha: ");
		int linha = sc.nextInt();
		System.out.print("coluna: ");
		int coluna = sc.nextInt();

		tab.continuandoJogo(linha, coluna);
		System.out.println(tab);

		while (tab.continuandoJogo(linha, coluna) == 0) {
			System.out.print("linha: ");
			linha = sc.nextInt();
			System.out.print("coluna: ");
			coluna = sc.nextInt();

			tab.continuandoJogo(linha, coluna);
			System.out.println(tab);
		}

		sc.close();
	}

}