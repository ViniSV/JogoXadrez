package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PartidaChess;
import chess.PecaChess;

public class peao extends PecaChess {

	private PartidaChess chessMatch;

	public peao(Tabuleiro tab, Cor cor, PartidaChess chessMatch) {
		super(tab, cor);
		this.chessMatch = chessMatch;
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

			// #Movimento especial En Passant white
			if (posi.getLinha() == 3) {
				Posicao left = new Posicao(posi.getLinha(), posi.getColuna() - 1);
				if (getTab().posicaoExiste(left) && temPecaOponente(left)
						&& getTab().peca(left) == chessMatch.getEnPassanVulneravel()) {
					mat[left.getLinha() - 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posi.getLinha(), posi.getColuna() + 1);
				if (getTab().posicaoExiste(right) && temPecaOponente(right)
						&& getTab().peca(right) == chessMatch.getEnPassanVulneravel()) {
					mat[right.getLinha() - 1][right.getColuna()] = true;
				}
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

			// #Movimento especial En Passant black
			if (posi.getLinha() == 4) {
				Posicao left = new Posicao(posi.getLinha(), posi.getColuna() - 1);
				if (getTab().posicaoExiste(left) && temPecaOponente(left)
						&& getTab().peca(left) == chessMatch.getEnPassanVulneravel()) {
					mat[left.getLinha() + 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posi.getLinha(), posi.getColuna() + 1);
				if (getTab().posicaoExiste(right) && temPecaOponente(right)
						&& getTab().peca(right) == chessMatch.getEnPassanVulneravel()) {
					mat[right.getLinha() + 1][right.getColuna()] = true;
				}
			}
		}
		return mat;
	}

}
