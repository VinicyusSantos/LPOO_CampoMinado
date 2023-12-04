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

	public boolean isAberto() {
		return aberto;
	}

	public ArrayList<Celula> getVizinhos() {
		return vizinhos;
	}

	public void setVizinhos(ArrayList<Celula> vizinhos) {
		this.vizinhos = vizinhos;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isBomba() {
		return bomba;
	}

	public void addBomba() {
		setBomba(true);
	}

	public void setBomba(boolean bomba) {
		this.bomba = bomba;
	}

	// adiciona vizinhos
	public void addVizinhos(Celula i) {
		this.vizinhos.add(i);
	}

	// verifica se tem bomba
	public boolean temBomba() {
		return bomba;
	}

	public boolean isBandeira() {
		return bandeira;
	}

	public void setBandeira(boolean bandeira) {
		this.bandeira = bandeira;
	}

	/*public int clique() {
		if (!aberto && !bandeira) {
			this.aberto = true;
			return (this.bomba) ? -1 : numeroBombasVizinhas();
		} else {
			return 0;
		}
	}*/
	
	public int clique() {
	    if (!aberto && !bandeira) {
	        this.aberto = true;

	        
	        if (!bomba && numeroBombasVizinhas() == 0) {
	            for (Celula vizinho : vizinhos) {
	                vizinho.clique();  
	            }
	        }

	        return (this.bomba) ? -1 : numeroBombasVizinhas();
	    } else {
	        return 0;  
	    }
	}

	// verificador de bombas vizinhas
	public int numeroBombasVizinhas() {
		int contadorBomba = 0;

		for (Celula vizinho : vizinhos) {
			if (vizinho.temBomba()) {
				contadorBomba++;
			}
		}
		return contadorBomba;
	}

	// marcar bandeira
	public void marcar() {
		if (isAberto()) {
			setBandeira(false);
		} else if (isBandeira()) {
			setBandeira(!isBandeira());
		} else {
			setBandeira(true);
		}
	}

	// desmarcar bandeira
	public void desmarcar(boolean mode) {
		this.bandeira = mode;
	}

	// verificar fim de jogo
	public boolean fimJogo() {
		if (aberto && bomba) {
			return true;
		} else {
			return false;
		}
	}

}
