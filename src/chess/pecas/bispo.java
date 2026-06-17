package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class bispo extends PecaChess {

	public bispo(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentoSPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		// nw
		p.setValores(posi.getLinha() - 1, posi.getColuna() - 1);
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ne
		p.setValores(posi.getLinha() - 1, posi.getColuna() + 1);
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// se
		p.setValores(posi.getLinha() + 1, posi.getColuna() + 1);
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// sw
		p.setValores(posi.getLinha() + 1, posi.getColuna() - 1);
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}