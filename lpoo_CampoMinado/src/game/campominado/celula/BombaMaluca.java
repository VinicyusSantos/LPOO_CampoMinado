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
        if (random.nextDouble() <= 0.1) {
            setBomba(false); // Torna a bomba maluca em uma célula normal
            notificarVizinhos(); // Notifica os vizinhos sobre a alteração
        }
    }

    // Método para notificar os vizinhos sobre a alteração no status da bomba maluca
    public void notificarVizinhos() {
        for (Celula vizinho : getVizinhos()) {
            if (!(vizinho instanceof BombaMaluca)) {
                // Atualiza o número de bombas vizinhas do vizinho
                int numBombasVizinhas = vizinho.numeroBombasVizinhas();
                if (isBomba()) {
                    numBombasVizinhas++;
                } else {
                    numBombasVizinhas--;
                }
                vizinho.setNumeroBombasVizinhas(numBombasVizinhas);
            }
        }
    }
}