package game.campominado.jogador;

public class Jogador {
	private String name;
	private int xp;
	
	public Jogador(String name, int xp) {
		this.name = name;
		this.xp = xp;
	}

	public String getName() {
		return name;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	
	
}
