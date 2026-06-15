package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Rei extends PecaChess {

	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posi) {
		PecaChess p = (PecaChess) getTab().peca(posi);
		return p == null || p.getCor() != getCor();
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

		return mat;
	}

}
