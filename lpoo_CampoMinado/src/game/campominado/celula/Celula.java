package game.campominado.celula;

import java.util.ArrayList;

public class Celula {
	// atributos da classe celula
	private boolean aberto;
	private boolean bomba;
	private boolean bandeira;

	ArrayList<Celula> vizinhos;

	// construtor para valores padrão
	public Celula() {
		this.aberto = false;
		this.bomba = false;
		this.bandeira = false;

		this.vizinhos = new ArrayList<>();
	}

	// metodos da celula
	
		//adiciona bomba
	public void addBomba() {
		bomba = true;
	}

		//adiciona vizinhos
	public void addVizinhos(Celula i) {
		this.vizinhos.add(i);
	}

		//verifica se tem bomba
	public boolean temBomba() {
		return bomba;
	}

		//metodo que permite clicar
	public int clique() {
		this.aberto = true;
		if (this.bomba == true) {
			return -1;
		} else {
			return numeroBombasVizinhas();
		}
	}

		//verificador de bombas vizinhas
	public int numeroBombasVizinhas() {
		int contadorBomba = 0;

		for (Celula vizinho : vizinhos) {
			if (vizinho.temBomba()) {
				contadorBomba++;
			}
		}

		return contadorBomba;
	}

		//marcar bandeira
	public void marcar() {
		if(this.aberto) {
			this.bandeira = false;
		}
		else if(this.bandeira) {
			this.bandeira = !this.bandeira;
		}
		else {
			this.bandeira = true;
		}
	}
		
		//desmarcar bandeira
	public void desmarcar(boolean mode) {
		this.bandeira = mode;
	}

		//verificar fim de jogo
	public boolean fimJogo() {
		if (aberto && bomba) {
			return true;
		} else {
			return false;
		}
	}
	
	//imprimindo cada celula
	@Override
	public String toString() {
		if (bomba && aberto) {
			return "*";
		} else if (!bomba && aberto) {
			return "" + numeroBombasVizinhas();
		} else if(bandeira) {
			return "F";
		} else {
			return "-";
		}
	}
}