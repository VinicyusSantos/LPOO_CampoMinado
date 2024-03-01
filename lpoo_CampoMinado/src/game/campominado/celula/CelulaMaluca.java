package game.campominado.celula;

import java.util.Random;

import game.campominado.exception.ValorAtributoInvalidoException;

public class CelulaMaluca extends Celula {
    private double nivelMaluquice;

    public CelulaMaluca(double nivelMaluquice) {
        super();
        this.nivelMaluquice = nivelMaluquice;

        if (Math.random() > nivelMaluquice) {
            setBomba(false);
        }
    }

    @Override
    public String toString() {
        if (isAberto()) {
            return (temBomba()) ? "*" : String.valueOf(getNumeroBombasVizinhas());
        } else if (isBandeira()) {
            return "F";
        } else {
            return (temBomba()) ? "A" : "-";
        }
    }

    @Override
    public void setBomba(boolean bomba) {
        if (!isAberto() && isBandeira() && Math.random() < nivelMaluquice) {
            super.setBomba(!bomba);
            // Notificação de vizinhos será feita depois
        } else {
            if (temBomba()) {
                // Notificação de vizinhos será feita depois
            }
            super.setBomba(bomba);
        }
    }

    private void notificarVizinhos() throws ValorAtributoInvalidoException {
        for (Celula vizinho : getVizinhos()) {
            if (!vizinho.temBomba() && !vizinho.isAberto()) {
                vizinho.setNumeroBombasVizinhas(vizinho.getNumeroBombasVizinhas() + (temBomba() ? 1 : -1));
            }
        }
    }

    private void notificarVizinhosRemocao() throws ValorAtributoInvalidoException {
        for (Celula vizinho : getVizinhos()) {
            if (!vizinho.temBomba() && !vizinho.isAberto()) {
                int novasBombasVizinhas = vizinho.getNumeroBombasVizinhas() - 1;
                vizinho.setNumeroBombasVizinhas(novasBombasVizinhas);
                if (novasBombasVizinhas == 0 && !vizinho.temBomba()) {
                    vizinho.clique();
                }
            }
        }
    }
    @Override
    public int clique() {
        try {
            if (!isAberto() && !isBandeira()) {
                setAberto(true);
        
                if (!temBomba() && getNumeroBombasVizinhas() == 0) {
                    for (Celula vizinho : getVizinhos()) {
                        vizinho.clique();
                    }
                }
        
                if (temBomba()) {
                    return -1;
                } else {
                    notificarVizinhos();
                    return getNumeroBombasVizinhas();
                }
            }
        } catch (ValorAtributoInvalidoException e) {
            e.printStackTrace(); // Or handle it appropriately
        }
        
        return 0;
    }
}
