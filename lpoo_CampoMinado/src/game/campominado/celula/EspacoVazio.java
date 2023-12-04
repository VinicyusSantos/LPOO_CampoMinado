package game.campominado.celula;

public class EspacoVazio extends Celula {
	public EspacoVazio() {
		super();
	}

	
	@Override
    public int numeroBombasVizinhas() {
        int contadorBomba = 0;

        for (Celula vizinho : vizinhos) {
            if (vizinho.temBomba()) {
                contadorBomba++;
            }
        }

        return contadorBomba;
    }
	
	@Override
    public String toString() {
        if (isAberto()) {
            return (isBomba()) ? "*" : String.valueOf(numeroBombasVizinhas());
        } else if(isBandeira()) {
            return "F";
        } else {
        	return "-";
        }
    }
}
