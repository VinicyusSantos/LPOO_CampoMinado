package game.campominado.tabuleiro;

import game.campominado.celula.Celula;

public interface JogoTabuleiro {
    int getLinhas();

    int getColunas();

    int getBombas();

    Celula[][] getCelulas();

    void setCelulas(Celula[][] celulas);

    void iniciarCampo();

    int continuandoJogo(int linha, int coluna, int id);

    void abrirVizinhas(int linha, int coluna);

    void addMinas();
}
