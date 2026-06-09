package chess;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;

public class PartidaChess {
	
	private Tabuleiro tabul;
	
	public PartidaChess () {
		tabul = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PecaChess[][] getPecas(){
		PecaChess[][] mat = new PecaChess[tabul.getLinhas()][tabul.getColunas()];
		for(int i=0 ; i < tabul.getLinhas(); i++) {
			for(int j=0; j < tabul.getColunas(); j++) {
				mat[i][j] = (PecaChess) tabul.peca(i,j);
			}
		}
		return mat;
	}
	
	private void setupInicial() {
		tabul.colocarPeca(new Torre(tabul, Cor.WHITE), new Posicao(2, 1));
		tabul.colocarPeca(new Rei(tabul, Cor.BLACK), new Posicao(0, 4));
		tabul.colocarPeca(new Rei(tabul, Cor.WHITE), new Posicao(7, 4));
	}
	
	
}
