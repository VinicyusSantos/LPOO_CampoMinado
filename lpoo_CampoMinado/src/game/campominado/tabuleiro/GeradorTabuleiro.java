package game.campominado.tabuleiro;

public class GeradorTabuleiro {
	private int linhas;
	private int colunas;
	private int bombas;
	
	public GeradorTabuleiro(int linhas, int colunas, int bombas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.bombas = bombas;
	}


	public Tabuleiro gerarTabuleiro() {
		Tabuleiro tab = new Tabuleiro(linhas, colunas, bombas);
        tab.iniciarCampo();
        return tab;
    }
	
}
