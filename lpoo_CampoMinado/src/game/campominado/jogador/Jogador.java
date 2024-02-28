package game.campominado.jogador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

	// Método para salvar informações do jogador em um arquivo
    public void salvarInfo(String arquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write(getName() + "," + getXp());
        }
    }

    // Método para carregar informações do jogador de um arquivo
    public static Jogador carregarInfo(String arquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();
            if (linha != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                int xp = Integer.parseInt(partes[1]);
                return new Jogador(nome, xp);
            }
        }
        return null;
    }
	
}
