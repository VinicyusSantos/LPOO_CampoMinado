package game.campominado.celula;

public class Bomba extends Celula {
	
	public Bomba() {
        super();
        setBomba(true);
    }
	
		//metodo para adicionar bomba
	
	public boolean temBomba() {
		return isBomba();
	}

	@Override
    public String toString() {
        //return (isBomba() && isAberto()) ? "*" : "A";
        if(isBomba() && isAberto()) {
        	return "*";
        }/*else if(isBomba() && !isAberto()) {
        	return "A";
        }*/ else if(isBandeira()) {
        	return "F";
        } else {
        	return "A";
        }
    }
}