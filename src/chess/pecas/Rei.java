package chess.pecas;

import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Rei extends PecaChess{

	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "R";
	}

}
