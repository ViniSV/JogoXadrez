package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PartidaChess;
import chess.PecaChess;

public class Rei extends PecaChess {

	private PartidaChess chessMatch;

	public Rei(Tabuleiro tab, Cor cor, PartidaChess chessMatch) {
		super(tab, cor);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posi) {
		PecaChess p = (PecaChess) getTab().peca(posi);
		return p == null || p.getCor() != getCor();
	}

	private boolean testTorreRoque(Posicao posi) {
		PecaChess p = (PecaChess) getTab().peca(posi);
		return p != null & p instanceof Torre && p.getCor() == getCor() && p.getContagemMove() == 0;
	}

	@Override
	public boolean[][] movimentoSPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		// above
		p.setValores(posi.getLinha() - 1, posi.getColuna());
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// below
		p.setValores(posi.getLinha() + 1, posi.getColuna());
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// left
		p.setValores(posi.getLinha(), posi.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// right
		p.setValores(posi.getLinha(), posi.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// northeast
		p.setValores(posi.getLinha() - 1, posi.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// northwest
		p.setValores(posi.getLinha() - 1, posi.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Southeast
		p.setValores(posi.getLinha() + 1, posi.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Southwest
		p.setValores(posi.getLinha() + 1, posi.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// #MovimentoEspecial Castling
		if (getContagemMove() == 0 && !chessMatch.getCheck()) {
			// #MovimentoEspecial Castling Small
			Posicao posT1 = new Posicao(posi.getLinha(), posi.getColuna() + 3);
			if (testTorreRoque(posT1)) {
				Posicao p1 = new Posicao(posi.getLinha(), posi.getColuna() + 1);
				Posicao p2 = new Posicao(posi.getLinha(), posi.getColuna() + 2);
				if (getTab().peca(p1) == null && getTab().peca(p2) == null) {
					mat[posi.getLinha()][posi.getColuna() + 2] = true;
				}

			}

			// #MovimentoEspecial Castling Big
			Posicao posT2 = new Posicao(posi.getLinha(), posi.getColuna() - 4);
			if (testTorreRoque(posT2)) {
				Posicao p1 = new Posicao(posi.getLinha(), posi.getColuna() - 1);
				Posicao p2 = new Posicao(posi.getLinha(), posi.getColuna() - 2);
				Posicao p3 = new Posicao(posi.getLinha(), posi.getColuna() - 3);
				if (getTab().peca(p1) == null && getTab().peca(p2) == null && getTab().peca(p3) == null) {
					mat[posi.getLinha()][posi.getColuna() - 2] = true;
				}

			}

		}

		return mat;
	}

}
