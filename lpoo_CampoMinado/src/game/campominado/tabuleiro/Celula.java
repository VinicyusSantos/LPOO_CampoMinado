package game.campominado.tabuleiro;

import java.util.ArrayList;

public class Celula {
	//atributos da classe celula
	private boolean aberto;
	private boolean bomba;
	private boolean bandeira;
	
	ArrayList<Celula> vizinhos;
	
	//construtor para valores padrão
	public Celula() {
		this.aberto = false;
		this.bomba = false;
		this.bandeira = false;
		
		this.vizinhos = new ArrayList<>();
	}
	
	//metodos da celula
	public void addBomba() {
		bomba = true;
	}
	
	public boolean temBomba() {
		return bomba;
	}
	
	public int clique() {
		this.aberto = true;
		if(this.bomba == true) {
			return -1;
		}else {
			return numeroBombasVizinhas();
		}
	}
	
	public int numeroBombasVizinhas() {
		
	}
	
	public void marcar(){
        this.bandeira = true;
    }  
	
	public boolean fimJogo() {
		if(aberto && bomba) {
			return true;
		}else {
			return false;
		}
	}
}
