package game.campominado.celula;

import java.util.Random;

public class BombaMaluca extends Bomba {
    
    // Construtor
    public BombaMaluca() {
        super();
    }

    // Método para verificar se a bomba maluca vira uma célula normal
    public void verificarExplosao() {
        Random random = new Random();
        // Defina uma probabilidade de 10% para a bomba maluca se transformar em uma célula normal
        if (random.nextDouble() <= 0.9) {
            setBomba(false); // Torna a bomba maluca em uma célula normal
        }
    }
}
