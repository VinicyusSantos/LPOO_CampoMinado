package game.campominado.tabuleiro;

import java.util.Random;

public class Tabuleiro {
	// atributos do tabuleiro
	private int linhas;
	private int colunas;
	private int bombas;
	private Celula[][] celulas;

	// construtor do tabuleiro
	public Tabuleiro(int linhas, int colunas, int bombas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.bombas = bombas;
	}

	// metodos

	// criando a matriz e cada celula
	public void iniciarCampo() {
		celulas = new Celula[linhas][colunas];
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				celulas[i][j] = new Celula();
			}
		}
<<<<<<< HEAD
		// adicionando vizinhos para verificar a qts de bombas
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (i > 0) {
					if (j > 0)
						celulas[i][j].addVizinhos(celulas[i - 1][j - 1]);
					celulas[i][j].addVizinhos(celulas[i - 1][j]);
					if (j < colunas - 1)
						celulas[i][j].addVizinhos(celulas[i - 1][j + 1]);
				}

				if (j > 0)
					celulas[i][j].addVizinhos(celulas[i][j - 1]);
				if (j < colunas - 1)
					celulas[i][j].addVizinhos(celulas[i][j + 1]);

				if (i < linhas - 1) {
					if (j > 0)
						celulas[i][j].addVizinhos(celulas[i + 1][j - 1]);
					celulas[i][j].addVizinhos(celulas[i + 1][j]);
					if (j < colunas - 1)
						celulas[i][j].addVizinhos(celulas[i + 1][j + 1]);
				}
			}
		}
=======
		//adicionando vizinhos para verificar a qts de bombas
		for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i > 0){
                    if (j > 0) celulas[i][j].addVizinhos(celulas[i-1][j-1]);
                    celulas[i][j].addVizinhos(celulas[i-1][j]);
                    if (j < colunas-1) celulas[i][j].addVizinhos(celulas[i-1][j+1]);
                }
                
                if (j > 0) celulas[i][j].addVizinhos(celulas[i][j-1]);                
                if (j < colunas-1)celulas[i][j].addVizinhos(celulas[i][j+1]);
                
                if(i < linhas -1){
                    if (j > 0)celulas[i][j].addVizinhos(celulas[i+1][j-1]);
                    celulas[i][j].addVizinhos(celulas[i+1][j]);
                    if (j < colunas-1)celulas[i][j].addVizinhos(celulas[i+1][j+1]);
                }
            }
        }
>>>>>>> branch 'main' of https://github.com/VinicyusSantos/LPOO_CampoMinado.git
		addMinas();
	}

<<<<<<< HEAD
	public int continuandoJogo(int linha, int coluna, int id) {
		if(id == 1) {
			celulas[linha][coluna].clique();
			if (celulas[linha][coluna].clique() == -1) {
				return 1;
			} else {
				return 0;
			}
		}
		else {
			celulas[linha][coluna].marcar();
			return 0;
		}
=======
	public int continuandoJogo(int linha, int coluna) {
			celulas[linha][coluna].clique();
			if(celulas[linha][coluna].clique() == -1) {
				return 1;
			}else {
				return 0;
			}
>>>>>>> branch 'main' of https://github.com/VinicyusSantos/LPOO_CampoMinado.git
	}
<<<<<<< HEAD

	/*
	 * metodo p/ adicionar minas aleatoriamente percorrendo o tabuleiro usando o
	 * addBombas da classe Celula
	 */
	public void addMinas() {
		Random random = new Random();
		int minasRestantes = bombas;

		while (minasRestantes > 0) {
			int linha = random.nextInt(linhas);
			int coluna = random.nextInt(colunas);

			if (!celulas[linha][coluna].temBomba()) {
				celulas[linha][coluna].addBomba();
				minasRestantes--;
			}
		}

	}

	// imprimindo a matriz das celulas
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				str += celulas[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}

}
=======
	
	/*metodo p/ adicionar minas aleatoriamente
	 * percorrendo o tabuleiro
	usando o addBombas da classe Celula*/
	public void addMinas() {
		Random random = new Random();
	    int minasRestantes = bombas;

	    while (minasRestantes > 0) {
	        int linha = random.nextInt(linhas);
	        int coluna = random.nextInt(colunas);

	        if (!celulas[linha][coluna].temBomba()) {
	            celulas[linha][coluna].addBomba();
	            minasRestantes--;
	        }
	    }
		
	}
	//imprimindo a matriz das celulas
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < linhas; i++) {
			for(int j = 0; j < colunas; j++) {
				str += celulas[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}
	
}
>>>>>>> branch 'main' of https://github.com/VinicyusSantos/LPOO_CampoMinado.git
