package game.campominado.celula;

import java.util.Random;

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
            notificarVizinhos();
        } else {
            super.setBomba(bomba);
        }
    }

    private void notificarVizinhos() {
        for (Celula vizinho : getVizinhos()) {
            if (!vizinho.temBomba() && !vizinho.isAberto()) {
                vizinho.setNumeroBombasVizinhas(vizinho.getNumeroBombasVizinhas() + (temBomba() ? 1 : -1));
            }
        }
    }

    @Override
    public int clique() {
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

        return 0;
    }
}
