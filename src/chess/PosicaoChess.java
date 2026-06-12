package chess;

import boardgame.Posicao;

public class PosicaoChess {

	private char coluna;
	private int linha;
	public PosicaoChess(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ChessException("Erro instanciando Posição, valores válidos são de a1 até h8.");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	public char getColuna() {
		return coluna;
	}
	public int getLinha() {
		return linha;
	}
	
	protected Posicao toPosicao() {
		return new Posicao(8- linha, coluna - 'a');
	}
	
	protected static PosicaoChess fromPosicao(Posicao position) {
		return new PosicaoChess((char)('a' - position.getColuna()), 8 - position.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
}
