package game.campominado.main;

import game.campominado.jogador.Jogador;
import game.campominado.tabuleiro.GeradorTabuleiro;
import game.campominado.tabuleiro.JogoTabuleiro;
import game.campominado.tabuleiro.Tabuleiro;
import game.campominado.tabuleiro.TabuleiroMaluco;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int linhasIniciante = 9;
        int colunasIniciante = 9;
        int bombasIniciante = 15;
        double nivelMaluquice = 0.8;
        int dificuldade = 1;
        
        int linhasIntermediario = 16;
        int colunasIntermediario = 16;
        int bombasIntermediario = 30;
        
        int linhasAvancado = 16;
        int colunasAvancado = 30;
        int bombasAvancado = 60;

        System.out.println("Escolha o modo de jogo:");
        System.out.println("1 - Campo Minado Tradicional");
        System.out.println("2 - Campo Minado Maluco");
        int modoJogo = sc.nextInt();
        
        if(modoJogo == 1) {
        	System.out.println("Dificuldade:");
        	System.out.println("1 - iniciante");
        	System.out.println("2 - medio");
        	System.out.println("3 - dificil");
        	dificuldade = sc.nextInt();
        }

        JogoTabuleiro tabuleiro = null;

        if (modoJogo == 1) {
            // Modo Tradicional
        	if(dificuldade == 1) {
        		tabuleiro = new GeradorTabuleiro(linhasIniciante, colunasIniciante, bombasIniciante, 1).gerarTabuleiro();
        	}
        	else if(dificuldade == 2) {
        		tabuleiro = new GeradorTabuleiro(linhasIntermediario, colunasIntermediario, bombasIntermediario, 2).gerarTabuleiro();
        	}
        	else if(dificuldade == 3) {
        		tabuleiro = new GeradorTabuleiro(linhasAvancado, colunasAvancado, bombasAvancado, 3).gerarTabuleiro();
        	}
            
        } else if (modoJogo == 2) {
            // Modo Maluco
            tabuleiro = new TabuleiroMaluco(linhasIniciante, colunasIniciante, bombasIniciante, nivelMaluquice);
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
