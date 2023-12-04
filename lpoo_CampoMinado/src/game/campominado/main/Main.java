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
		
		System.out.print("NOME JOGADOR 1: ");
		String nomeJogador1 = sc.next();
		System.out.print("NOME JOGADOR 2: ");
		String nomeJogador2 = sc.next();
		
		Jogador[] jogadores = new Jogador[2];
        jogadores[0] = new Jogador(nomeJogador1, 0);
        jogadores[1] = new Jogador(nomeJogador2, 0);

        int indiceJogadorAtual = 0;

        while (true) {
            System.out.println(jogadores[indiceJogadorAtual].getName() + ", é a sua vez:");

            System.out.print("Abrir[1] ou Colocar Bandeira?[2] ou Retirar Bandeira?[3]: ");
            int opcao = sc.nextInt();
            System.out.print("Linha: ");
            int linha = sc.nextInt();
            System.out.print("Coluna: ");
            int coluna = sc.nextInt();

            int resultado = tab.continuandoJogo(linha, coluna, opcao);
            System.out.println(tab);

            if (resultado == 1) {
                System.out.println(jogadores[indiceJogadorAtual].getName() + " acertou em uma bomba! Jogo encerrado!");
                break;
            } else if (resultado == 0) {
                jogadores[indiceJogadorAtual].setXp(jogadores[indiceJogadorAtual].getXp() + 1);
            }

            // Alternar para o outro jogador
            indiceJogadorAtual = (indiceJogadorAtual + 1) % 2;
        }

        sc.close();
    }

}