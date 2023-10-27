package game.campominado.jogador;

public class Jogador {
	private String name;
	private double xp;
	
	public Jogador(String name, double xp) {
		this.name = name;
		this.xp = xp;
	}

	public String getName() {
		return name;
	}

	public double getXp() {
		return xp;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	
	
}
