package chess;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;

public abstract class PecaChess extends Peca {

	private Cor cor;
	private int contagemMove;

	public PecaChess(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	public int getContagemMove() {
		return contagemMove;
	}

	public void aumentarContagemMove() {
		contagemMove++;
	}

	public void diminuirContagemMove() {
		contagemMove--;
	}

	public PosicaoChess getPosicaoChess() {
		return PosicaoChess.fromPosicao(posi);
	}

	protected boolean temPecaOponente(Posicao posi) {
		PecaChess p = (PecaChess) getTab().peca(posi);
		return p != null && p.getCor() != cor;
	}

}
