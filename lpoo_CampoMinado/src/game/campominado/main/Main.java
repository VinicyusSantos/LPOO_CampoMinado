package game.campominado.main;

import game.campominado.jogador.Jogador;
import game.campominado.tabuleiro.GeradorTabuleiro;
import game.campominado.tabuleiro.Tabuleiro;
import game.campominado.tabuleiro.TabuleiroMaluco;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int linhas = 9;
        int colunas = 9;
        int bombas = 15;
        double nivelMaluquice = 0.2;

        System.out.println("Escolha o modo de jogo:");
        System.out.println("1 - Campo Minado Tradicional");
        System.out.println("2 - Campo Minado Maluco");
        int modoJogo = sc.nextInt();

        Tabuleiro tabuleiro;

        if (modoJogo == 1) {
            // Modo Tradicional
            tabuleiro = new GeradorTabuleiro(linhas, colunas, bombas).gerarTabuleiro();
        } else if (modoJogo == 2) {
            // Modo Maluco
            tabuleiro = new TabuleiroMaluco(linhas, colunas, bombas, nivelMaluquice);
        } else {
            System.out.println("Modo de jogo inválido. Saindo.");
            return;
        }

        // Iniciando o jogo
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

            int resultado = tabuleiro.continuandoJogo(linha, coluna, opcao);
            System.out.println(tabuleiro);

            if (resultado == 1) {
                System.out.println(jogadores[indiceJogadorAtual].getName() + " acertou em uma bomba! Jogo encerrado!");
                break;
            } else if (resultado == 0) {
                jogadores[indiceJogadorAtual].setXp(jogadores[indiceJogadorAtual].getXp() + 1);
            }

            // outro jogador
            indiceJogadorAtual = (indiceJogadorAtual + 1) % 2;
        }

        sc.close();
    }
}
