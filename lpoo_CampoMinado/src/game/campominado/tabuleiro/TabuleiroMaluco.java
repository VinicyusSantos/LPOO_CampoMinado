package game.campominado.tabuleiro;

import java.util.Random;

import game.campominado.celula.Bomba;
import game.campominado.celula.Celula;
import game.campominado.celula.CelulaMaluca;

public class TabuleiroMaluco extends Tabuleiro {
    private double nivelMaluquice;

    public TabuleiroMaluco(int linhas, int colunas, int bombas, double nivelMaluquice) {
        super(linhas, colunas, bombas);
        this.nivelMaluquice = nivelMaluquice;
        iniciarCampo();
    }

    @Override
    public void addMinas() {
        Random random = new Random();
        int minasRestantes = getBombas();

        while (minasRestantes > 0) {
            int linha = random.nextInt(getLinhas());
            int coluna = random.nextInt(getColunas());

            if (!(getCelulas()[linha][coluna] instanceof Bomba)) {
                getCelulas()[linha][coluna] = new CelulaMaluca(nivelMaluquice);
                minasRestantes--;
            }
        }

        // Adicionando vizinhos para as bombas
        for (int i = 0; i < getLinhas(); i++) {
            for (int j = 0; j < getColunas(); j++) {
                if (getCelulas()[i][j] instanceof Bomba) {
                    // Adiciona todos os vizinhos para as bombas
                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++) {
                            if (x >= 0 && x < getLinhas() && y >= 0 && y < getColunas() && !(x == i && y == j)) {
                                getCelulas()[x][y].addVizinhos(getCelulas()[i][j]);
                            }
                        }
                    }
                }
            }
        }
    }

    
    public void reiniciarTabuleiro() {
        iniciarCampo();
        addMinas();
    }
    

    @Override
    public void abrirVizinhas(int linha, int coluna) {
        Celula celula = getCelulas()[linha][coluna];

        if (celula.isAberto()) {
            return;
        }

        int resultado = celula.clique();

        if (resultado != -1 && celula.numeroBombasVizinhas() == 0) {
            for (int i = linha - 1; i <= linha + 1; i++) {
                for (int j = coluna - 1; j <= coluna + 1; j++) {
                    if (i >= 0 && i < getLinhas() && j >= 0 && j < getColunas()) {

                        if (i != linha || j != coluna) {
                            abrirVizinhas(i, j);
                        }
                    }
                }
            }
        }
    }

 

    @Override
    public void iniciarCampo() {
        setCelulas(new Celula[getLinhas()][getColunas()]);
        Random random = new Random();

        for (int i = 0; i < getLinhas(); i++) {
            for (int j = 0; j < getColunas(); j++) {
                double probabilidadeBomba = random.nextDouble();

                if (probabilidadeBomba < nivelMaluquice) {
                    getCelulas()[i][j] = new CelulaMaluca(nivelMaluquice);
                    getCelulas()[i][j].addBomba();
                } else {
                    getCelulas()[i][j] = new CelulaMaluca(nivelMaluquice);
                }
            }
        }
    }
}
