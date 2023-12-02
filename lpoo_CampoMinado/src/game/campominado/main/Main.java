package game.campominado.main;

import game.campominado.jogador.Jogador;
import game.campominado.tabuleiro.GeradorTabuleiro;
import java.util.Scanner;
import game.campominado.tabuleiro.Tabuleiro;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int linhas = 9;
		int colunas = 9;
		int bombas = 15;

		//gerando o tabuleiro
		GeradorTabuleiro n = new GeradorTabuleiro(linhas, colunas, bombas);
		Tabuleiro tab = n.gerarTabuleiro();
		System.out.println(tab);
		
		//iniciando jogo
		
		/*System.out.print("NOME JOGADOR 1:");
		String name1 = sc.next();
		System.out.print("NOME JOGADOR 2:");
		String name2 = sc.next();
		
		Jogador player1 = new Jogador(name1, 0);
		Jogador player2 = new Jogador(name2, 0);*/
		
		System.out.print("Abrir[1] ou Bandeira?[2] ou Desmarcar?[3]");
		int option = sc.nextInt();
		System.out.print("linha: ");
		int linha = sc.nextInt();
		System.out.print("coluna: ");
		int coluna = sc.nextInt();

		tab.continuandoJogo(linha, coluna, option);
		System.out.println(tab);

		while (tab.continuandoJogo(linha, coluna, option) == 0) {
			System.out.print("Abrir[1] ou Bandeira?[2] ou Desmarcar?[3]");
			option = sc.nextInt();
			System.out.print("linha: ");
			linha = sc.nextInt();
			System.out.print("coluna: ");
			coluna = sc.nextInt();

			tab.continuandoJogo(linha, coluna, option);
			System.out.println(tab);
		}

		sc.close();
	}

}