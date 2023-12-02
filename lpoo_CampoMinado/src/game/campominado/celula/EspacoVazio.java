package game.campominado.celula;

public class EspacoVazio extends Celula {
	public EspacoVazio() {
		super();
	}

	@Override
    public String toString() {
        if (isAberto()) {
            return (isBomba()) ? "*" : String.valueOf(numeroBombasVizinhas());
        } else {
            return "-";
        }
    }
}
