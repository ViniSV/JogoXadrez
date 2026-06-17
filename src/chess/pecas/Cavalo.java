package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Cavalo extends PecaChess {

	public Cavalo(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao posi) {
		PecaChess p = (PecaChess) getTab().peca(posi);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentoSPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		p.setValores(posi.getLinha() - 1, posi.getColuna() - 2);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi.getLinha() - 2, posi.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi.getLinha() - 2, posi.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi.getLinha() - 1, posi.getColuna() + 2);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi.getLinha() + 1, posi.getColuna() + 2);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi.getLinha() + 2, posi.getColuna() + 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi.getLinha() + 2, posi.getColuna() - 1);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi.getLinha() + 1, posi.getColuna() - 2);
		if (getTab().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
