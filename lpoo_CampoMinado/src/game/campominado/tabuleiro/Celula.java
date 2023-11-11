package game.campominado.tabuleiro;

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
	public void addBomba() {
		bomba = true;
	}
<<<<<<< HEAD

	public void addVizinhos(Celula i) {
		this.vizinhos.add(i);
	}

=======
	
	public void addVizinhos(Celula i) {
		this.vizinhos.add(i);
	}
	
>>>>>>> branch 'main' of https://github.com/VinicyusSantos/LPOO_CampoMinado.git
	public boolean temBomba() {
		return bomba;
	}

	public int clique() {
		this.aberto = true;
		if (this.bomba == true) {
			return -1;
		} else {
			return numeroBombasVizinhas();
		}
	}

	public int numeroBombasVizinhas() {
<<<<<<< HEAD
		int contadorBomba = 0;

		for (Celula vizinho : vizinhos) {
			if (vizinho.temBomba()) {
				contadorBomba++;
			}
		}

		return contadorBomba;
=======
		 int contadorBomba = 0;

		    for (Celula vizinho : vizinhos) {
		        if (vizinho.temBomba()) {
		            contadorBomba++;
		        }
		    }

		    return contadorBomba;
>>>>>>> branch 'main' of https://github.com/VinicyusSantos/LPOO_CampoMinado.git
	}

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

	public boolean fimJogo() {
		if (aberto && bomba) {
			return true;
		} else {
			return false;
		}
	}
<<<<<<< HEAD

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
=======
	
	@Override
	public String toString() {
		if(bomba && aberto) {
			return "*";
		}
		else if(!bomba && aberto){
			return ""+numeroBombasVizinhas();
		}else {
			return "-";
		}
	}
}
>>>>>>> branch 'main' of https://github.com/VinicyusSantos/LPOO_CampoMinado.git
