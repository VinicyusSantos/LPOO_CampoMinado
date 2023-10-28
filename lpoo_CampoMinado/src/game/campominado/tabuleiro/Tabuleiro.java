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

	//metodos
	
	//criando a matriz e cada celula
	public void iniciarCampo() {
		celulas = new Celula[linhas][colunas];
		for(int i = 0; i < linhas; i++) {
			for(int j = 0; j < colunas; j++) {
				celulas[i][j] = new Celula();
			}
		}
		addMinas();
	}
	
	public void addMinas() {
		/*metodo p/ adicionar minas aleatoriamente
		 * percorrendo o tabuleiro
		usando o addBombas da classe Celula*/
	}
	
	
}