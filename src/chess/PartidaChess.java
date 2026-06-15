package chess;

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
	
	private void colocarNovaPeca(char coluna, int linha, PecaChess peca) {
		tabul.colocarPeca(peca, new PosicaoChess(coluna, linha).toPosicao() );
	}
	
	private void setupInicial() {
		colocarNovaPeca('c', 1, new Torre(tabul, Cor.WHITE));
		colocarNovaPeca('c', 2, new Torre(tabul, Cor.WHITE));
		colocarNovaPeca('d', 2, new Torre(tabul, Cor.WHITE));
		colocarNovaPeca('e', 2, new Torre(tabul, Cor.WHITE));
		colocarNovaPeca('e', 1, new Torre(tabul, Cor.WHITE));
        colocarNovaPeca('d', 1, new Rei(tabul, Cor.WHITE));

        colocarNovaPeca('c', 7, new Torre(tabul, Cor.BLACK));
        colocarNovaPeca('c', 8, new Torre(tabul, Cor.BLACK));
        colocarNovaPeca('d', 7, new Torre(tabul, Cor.BLACK));
        colocarNovaPeca('e', 7, new Torre(tabul, Cor.BLACK));
        colocarNovaPeca('e', 8, new Torre(tabul, Cor.BLACK));
        colocarNovaPeca('d', 8, new Rei(tabul, Cor.BLACK));
	}
	
	
}
