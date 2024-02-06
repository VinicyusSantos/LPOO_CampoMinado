package game.campominado.tabuleiro;

import game.campominado.celula.Celula;
import game.campominado.celula.Bomba;
import game.campominado.celula.VizinhaBomba;
import game.campominado.exception.ValorInvalidoException;
import game.campominado.celula.EspacoVazio;
import java.util.Random;

public abstract class Tabuleiro implements JogoTabuleiro {
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
		iniciarCampo();
	}

	// metodos

	public int getLinhas() {
        return linhas;
    }
	
	public void setLinhas(int linhas) {
	    if (linhas <= 0) {
	        throw new ValorInvalidoException("O número de linhas deve ser maior que zero.");
	    }
	    this.linhas = linhas;
	}

    public int getColunas() {
        return colunas;
    }

    public int getBombas() {
        return bombas;
    }

    public Celula[][] getCelulas() {
        return celulas;
    }
    
    public void setCelulas(Celula[][] celulas) {
        this.celulas = celulas;
    }
    
	// criando a matriz e cada celula
	public void iniciarCampo() {
		celulas = new Celula[linhas][colunas];
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				celulas[i][j] = new EspacoVazio();
			}
		}
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
		addMinas();
	}

	// metodo p/ continuar jogo

	public int continuandoJogo(int linha, int coluna, int id) {
	    if (id == 1) {
	        Celula celula = celulas[linha][coluna];
	        int result = celula.clique(); 

	        if (result == -1) {
	            return 1;  
	        } else {
	            return 0;  
	        }
	    } else if (id == 2) {
	        celulas[linha][coluna].marcar();
	        return 0;
	    } else {
	        celulas[linha][coluna].desmarcar(false);
	        return 0;
	    }
	}

	public void abrirVizinhas(int linha, int coluna) {
		Celula celula = celulas[linha][coluna];

		if (celula.isAberto()) {
			return;
		}

		int resultado = celula.clique();

		if (resultado != -1 && celula.numeroBombasVizinhas() == 0) {
			for (int i = linha - 1; i <= linha + 1; i++) {
				for (int j = coluna - 1; j <= coluna + 1; j++) {
					if (i >= 0 && i < linhas && j >= 0 && j < colunas) {

						if (i != linha || j != coluna) {
							abrirVizinhas(i, j);
						}
					}
				}
			}
		}
	}

	/*
	 * metodo p/ adicionar minas aleatoriamente percorrendo o tabuleiro usando o
	 * addBombas da classe Celula
	 */
	public void addMinas() {
		if(celulas == null) {
			return;
		}
		Random random = new Random();
		int minasRestantes = bombas;

		while (minasRestantes > 0) {
			int linha = random.nextInt(linhas);
			int coluna = random.nextInt(colunas);

			if (!(celulas[linha][coluna] instanceof Bomba)) {
				celulas[linha][coluna] = new Bomba();
				minasRestantes--;
			}
		}

		// Adicionando vizinhos para as bombas
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (celulas[i][j] instanceof Bomba) {
					// Adiciona todos os vizinhos para as bombas
					for (int x = i - 1; x <= i + 1; x++) {
						for (int y = j - 1; y <= j + 1; y++) {
							if (x >= 0 && x < linhas && y >= 0 && y < colunas && !(x == i && y == j)) {
								celulas[x][y].addVizinhos(celulas[i][j]);
							}
						}
					}
				}
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
