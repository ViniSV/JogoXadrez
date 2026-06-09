package chess.pecas;

import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Torre extends PecaChess{

	public Torre(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString(){
		return "T";
	}

}
