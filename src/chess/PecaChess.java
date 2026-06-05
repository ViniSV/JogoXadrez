package chess;

import boardgame.Peca;
import boardgame.Tabuleiro;

public class PecaChess extends Peca {

	private Cor cor;

	public PecaChess(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	
	
}
