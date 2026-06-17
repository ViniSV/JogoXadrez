package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class peao extends PecaChess {

	public peao(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentoSPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.WHITE) {
			p.setValores(posi.getLinha() - 1, posi.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi.getLinha() - 2, posi.getColuna());
			Posicao p2 = new Posicao(posi.getLinha() - 1, posi.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().temPeca(p) && getTab().posicaoExiste(p2) && !getTab().temPeca(p2)
					&& getContagemMove() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi.getLinha() - 1, posi.getColuna() - 1);
			if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi.getLinha() - 1, posi.getColuna() + 1);
			if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		} else {
			p.setValores(posi.getLinha() + 1, posi.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi.getLinha() + 2, posi.getColuna());
			Posicao p2 = new Posicao(posi.getLinha() + 1, posi.getColuna());
			if (getTab().posicaoExiste(p) && !getTab().temPeca(p) && getTab().posicaoExiste(p2) && !getTab().temPeca(p2)
					&& getContagemMove() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi.getLinha() + 1, posi.getColuna() - 1);
			if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi.getLinha() + 1, posi.getColuna() + 1);
			if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}

		return mat;
	}

}
