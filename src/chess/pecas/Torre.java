package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaChess;

public class Torre extends PecaChess {

	public Torre(Tabuleiro tab, Cor cor) {
		super(tab, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentoSPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		Posicao p = new Posicao(0, 0);

		// above
		p.setValores(posi.getLinha() - 1, posi.getColuna());
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// left
		p.setValores(posi.getLinha(), posi.getColuna() - 1);
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// right
		p.setValores(posi.getLinha(), posi.getColuna() +1);
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// below
		p.setValores(posi.getLinha() + 1, posi.getColuna());
		while (getTab().posicaoExiste(p) && !getTab().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTab().posicaoExiste(p) && temPecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
