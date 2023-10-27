package game.campominado.tabuleiro;

public class Tabuleiro {
	//atributos do tabuleiro
	private int linhas;
	private int colunas;
	private int bombas;
	private Celula[][] celulas;
	
	//construtor do tabuleiro
	public Tabuleiro(int linhas, int colunas, int bombas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.bombas = bombas;
	}

	/*public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}*/
	
	//metodos
	
	//criando a matriz e cada celula
	public void iniciarCampo() {
		celulas = new Celula[linhas][colunas];
		for(int i = 0; i < linhas; i++) {
			for(int j = 0; i < colunas; j++) {
				celulas[i][j] = new Celula();
			}
		}
	}
	
	public void mostrarTabuleiro() {
		
	}
	
	public void adicionandoBombas() {
		
	}
	
	
}
