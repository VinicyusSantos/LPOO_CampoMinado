package game.campominado.tabuleiro;

public class GeradorTabuleiro {
	private int linhas;
	private int colunas;
	private int bombas;
	private int dificuldade;

	public GeradorTabuleiro(int linhas, int colunas, int bombas, int dificuldade) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.bombas = bombas;
		this.dificuldade = dificuldade;
	}

	public Tabuleiro gerarTabuleiro() {
		if(dificuldade == 1) {
			TabuleiroIniciante tab = new TabuleiroIniciante(linhas, colunas, bombas);
			tab.iniciarCampo();
			return tab;
		}
		else if(dificuldade == 2) {
			TabuleiroIntermediario tab = new TabuleiroIntermediario(linhas, colunas, bombas);
			tab.iniciarCampo();
			return tab;
		}
		else {
			TabuleiroAvancado tab = new TabuleiroAvancado(linhas, colunas, bombas);
			tab.iniciarCampo();
			return tab;
		}
	}

}
