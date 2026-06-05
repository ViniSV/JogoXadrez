package chess;

import boardgame.Tabuleiro;

public class PartidaChess {
	
	private Tabuleiro tabul;
	
	public PartidaChess () {
		tabul = new Tabuleiro(8, 8);
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
	
	
}
