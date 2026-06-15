package chess;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;

public abstract class PecaChess extends Peca {

	private Cor cor;

	public PecaChess(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean temPecaOponente(Posicao posi) {
		PecaChess p = (PecaChess)getTab().peca(posi);
		return p != null && p.getCor() != cor;
	}
	
	
}
