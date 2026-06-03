package boardgame;

public class Peca {

	protected Posicao posi;
	private Tabuleiro tab;
	public Peca(Tabuleiro tab) {
		this.tab = tab;
		posi = null;
	}
	
	protected Tabuleiro getTab() {
		return tab;
	}
	
	
	
}
