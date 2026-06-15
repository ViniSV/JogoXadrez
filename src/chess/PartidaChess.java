package chess;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;

public class PartidaChess {

	private Tabuleiro tabul;

	public PartidaChess() {
		tabul = new Tabuleiro(8, 8);
		setupInicial();
	}

	public PecaChess[][] getPecas() {
		PecaChess[][] mat = new PecaChess[tabul.getLinhas()][tabul.getColunas()];
		for (int i = 0; i < tabul.getLinhas(); i++) {
			for (int j = 0; j < tabul.getColunas(); j++) {
				mat[i][j] = (PecaChess) tabul.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] proxMovimento(PosicaoChess posicaoFonte){
		Posicao posi = posicaoFonte.toPosicao();
		validarPosicaoFonte(posi);
		return tabul.peca(posi).movimentoSPossiveis();
	}
	
	
	public PecaChess fazerNovoMovimento(PosicaoChess posicaoFonte, PosicaoChess posicaoAlvo) {
		Posicao fonte = posicaoFonte.toPosicao();
		Posicao alvo = posicaoAlvo.toPosicao();
		validarPosicaoFonte(fonte);
		validarPosicaoAlvo(fonte, alvo);
		Peca pecaCapturada = fazerMovimento(fonte, alvo);
		return (PecaChess) pecaCapturada;
	}

	private Peca fazerMovimento(Posicao fonte, Posicao alvo) {
		Peca p = tabul.retirarPeca(fonte);
		Peca capturada = tabul.retirarPeca(alvo);
		tabul.colocarPeca(p, alvo);
		return capturada;
	}

	private void validarPosicaoFonte(Posicao posi) {
		if (!tabul.temPeca(posi)) {
			throw new ChessException("Não tem uma peça na posição fonte.");
		}
		if (!tabul.peca(posi).temAlgumMovimento()) {
			throw new ChessException("Não tem movimentos possíveis para esta peça.");
		}
	}

	private void validarPosicaoAlvo(Posicao fonte, Posicao alvo) {
		if (!tabul.peca(fonte).movimentOPossivel(alvo)) {
			throw new ChessException("A peça escolhida não pode se mover para a posição escolhida.");
		}
	}

	private void colocarNovaPeca(char coluna, int linha, PecaChess peca) {
		tabul.colocarPeca(peca, new PosicaoChess(coluna, linha).toPosicao());
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
