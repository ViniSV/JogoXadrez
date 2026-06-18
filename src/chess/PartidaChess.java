package chess;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.pecas.Cavalo;
import chess.pecas.Rainha;
import chess.pecas.Rei;
import chess.pecas.Torre;
import chess.pecas.bispo;
import chess.pecas.peao;

public class PartidaChess {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabul;
	private boolean check;
	private boolean checkMate;
	private PecaChess enPassanVulneravel;
	private PecaChess promocao;

	private List<Peca> pecaNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaChess() {
		tabul = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		setupInicial();
	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public PecaChess getEnPassanVulneravel() {
		return enPassanVulneravel;
	}

	public PecaChess getPromocao() {
		return promocao;
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

	public boolean[][] proxMovimento(PosicaoChess posicaoFonte) {
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

		if (testeDeCheck(jogadorAtual)) {
			desfazerMovimento(fonte, alvo, pecaCapturada);
			throw new ChessException("Você não pode se colocar em cheque.");
		}

		PecaChess pecaMovida = (PecaChess) tabul.peca(alvo);

		//# movimento especial promocao
		promocao = null;
		if (pecaMovida instanceof peao) {
			if ((pecaMovida.getCor() == Cor.WHITE && alvo.getLinha() == 0) || pecaMovida.getCor() == Cor.BLACK && alvo.getLinha() ==7 ){
				promocao = (PecaChess)tabul.peca(alvo);
				promocao = trocarPecaPromovida("Q");
			}
		}
		
		check = (testeDeCheck(oponente(jogadorAtual))) ? true : false;

		if (testeDeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} else {
			proxTurno();
		}

		// #Movimento especial En Passant
		if (pecaMovida instanceof peao
				&& (alvo.getLinha() == fonte.getLinha() - 2 || alvo.getLinha() == fonte.getLinha() + 2)) {
			enPassanVulneravel = pecaMovida;
		} else {
			enPassanVulneravel = null;
		}

		return (PecaChess) pecaCapturada;
	}

	public PecaChess trocarPecaPromovida(String type) {
		if (promocao == null) {
			throw new IllegalStateException("Não tem peça para ser promovida.");
		}
		if (!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) {
			throw new InvalidParameterException("Tipo para promoção é inválido.");
		}
		
		Posicao pos = promocao.getPosicaoChess().toPosicao();
		Peca p = tabul.retirarPeca(pos);
		pecaNoTabuleiro.remove(p);
		
		PecaChess pecaNova = novaPeca(type, promocao.getCor());
		tabul.colocarPeca(pecaNova, pos);
		pecaNoTabuleiro.add(pecaNova);
		return pecaNova;
	}
	
	private PecaChess novaPeca(String type, Cor cor) {
		if (type.equals("B")) return new bispo(tabul, cor);
		if (type.equals("C")) return new Cavalo(tabul, cor);
		if (type.equals("Q")) return new Rainha(tabul, cor);
		return new Torre(tabul, cor);
	}
	
	
	private Peca fazerMovimento(Posicao fonte, Posicao alvo) {
		PecaChess p = (PecaChess) tabul.retirarPeca(fonte);
		p.aumentarContagemMove();
		Peca capturada = tabul.retirarPeca(alvo);
		tabul.colocarPeca(p, alvo);

		if (capturada != null) {
			pecaNoTabuleiro.remove(capturada);
			pecasCapturadas.add(capturada);
		}

		// #MovimentoEspecial Castling Small
		if (p instanceof Rei && alvo.getColuna() == fonte.getColuna() + 2) {
			Posicao fonteT = new Posicao(fonte.getLinha(), fonte.getColuna() + 3);
			Posicao alvoT = new Posicao(fonte.getLinha(), fonte.getColuna() + 1);
			PecaChess torre = (PecaChess) tabul.retirarPeca(fonteT);
			tabul.colocarPeca(torre, alvoT);
			torre.aumentarContagemMove();
		}

		// #MovimentoEspecial Castling Big
		if (p instanceof Rei && alvo.getColuna() == fonte.getColuna() - 2) {
			Posicao fonteT = new Posicao(fonte.getLinha(), fonte.getColuna() - 4);
			Posicao alvoT = new Posicao(fonte.getLinha(), fonte.getColuna() - 1);
			PecaChess torre = (PecaChess) tabul.retirarPeca(fonteT);
			tabul.colocarPeca(torre, alvoT);
			torre.aumentarContagemMove();
		}

		// #Movimento especial En Passant
		if (p instanceof peao) {
			if (fonte.getColuna() != alvo.getColuna() && capturada == null) {
				Posicao posiPeao;
				if (p.getCor() == Cor.WHITE) {
					posiPeao = new Posicao(alvo.getLinha() + 1, alvo.getColuna());
				} else {
					posiPeao = new Posicao(alvo.getLinha() - 1, alvo.getColuna());
				}
				capturada = tabul.retirarPeca(posiPeao);
				pecasCapturadas.add(capturada);
				pecaNoTabuleiro.remove(capturada);
			}
		}

		return capturada;
	}

	private void desfazerMovimento(Posicao fonte, Posicao alvo, Peca capturada) {
		PecaChess p = (PecaChess) tabul.retirarPeca(alvo);
		p.diminuirContagemMove();
		tabul.colocarPeca(p, fonte);

		if (capturada != null) {
			tabul.colocarPeca(capturada, alvo);
			pecasCapturadas.remove(capturada);
			pecaNoTabuleiro.add(capturada);
		}

		// #MovimentoEspecial Castling Small
		if (p instanceof Rei && alvo.getColuna() == fonte.getColuna() + 2) {
			Posicao fonteT = new Posicao(fonte.getLinha(), fonte.getColuna() + 3);
			Posicao alvoT = new Posicao(fonte.getLinha(), fonte.getColuna() + 1);
			PecaChess torre = (PecaChess) tabul.retirarPeca(alvoT);
			tabul.colocarPeca(torre, fonteT);
			torre.diminuirContagemMove();
		}

		// #MovimentoEspecial Castling Big
		if (p instanceof Rei && alvo.getColuna() == fonte.getColuna() - 2) {
			Posicao fonteT = new Posicao(fonte.getLinha(), fonte.getColuna() - 4);
			Posicao alvoT = new Posicao(fonte.getLinha(), fonte.getColuna() - 1);
			PecaChess torre = (PecaChess) tabul.retirarPeca(alvoT);
			tabul.colocarPeca(torre, fonteT);
			torre.diminuirContagemMove();
		}

		// #Movimento especial En Passant
		if (p instanceof peao) {
			if (fonte.getColuna() != alvo.getColuna() && capturada == enPassanVulneravel) {
				PecaChess peao = (PecaChess) tabul.retirarPeca(alvo);
				Posicao posiPeao;
				if (p.getCor() == Cor.WHITE) {
					posiPeao = new Posicao(3, alvo.getColuna());
				} else {
					posiPeao = new Posicao(4, alvo.getColuna());
				}
				tabul.colocarPeca(peao, posiPeao);
			}
		}

	}

	private void validarPosicaoFonte(Posicao posi) {
		if (!tabul.temPeca(posi)) {
			throw new ChessException("Não tem uma peça na posição fonte.");
		}

		if (jogadorAtual != ((PecaChess) tabul.peca(posi)).getCor()) {
			throw new ChessException("A peça escolhida não é sua.");
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

	private void proxTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private void colocarNovaPeca(char coluna, int linha, PecaChess peca) {
		tabul.colocarPeca(peca, new PosicaoChess(coluna, linha).toPosicao());
		pecaNoTabuleiro.add(peca);
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private PecaChess rei(Cor cor) {
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaChess) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaChess) p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor " + cor + " no tabuleiro.");
	}

	private boolean testeDeCheck(Cor cor) {
		Posicao reiPosicao = rei(cor).getPosicaoChess().toPosicao();
		List<Peca> pecaOponente = pecaNoTabuleiro.stream().filter(x -> ((PecaChess) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecaOponente) {
			boolean[][] mat = p.movimentoSPossiveis();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeDeCheckMate(Cor cor) {
		if (!testeDeCheck(cor)) {
			return false;
		}
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaChess) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentoSPossiveis();
			for (int i = 0; i < tabul.getLinhas(); i++) {
				for (int j = 0; j < tabul.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao fonte = ((PecaChess) p).getPosicaoChess().toPosicao();
						Posicao alvo = new Posicao(i, j);
						Peca capturada = fazerMovimento(fonte, alvo);
						boolean testeCheck = testeDeCheck(cor);
						desfazerMovimento(fonte, alvo, capturada);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void setupInicial() {
		colocarNovaPeca('a', 1, new Torre(tabul, Cor.WHITE));
		colocarNovaPeca('b', 1, new Cavalo(tabul, Cor.WHITE));
		colocarNovaPeca('c', 1, new bispo(tabul, Cor.WHITE));
		colocarNovaPeca('d', 1, new Rainha(tabul, Cor.WHITE));
		colocarNovaPeca('e', 1, new Rei(tabul, Cor.WHITE, this));
		colocarNovaPeca('f', 1, new bispo(tabul, Cor.WHITE));
		colocarNovaPeca('g', 1, new Cavalo(tabul, Cor.WHITE));
		colocarNovaPeca('h', 1, new Torre(tabul, Cor.WHITE));
		colocarNovaPeca('a', 2, new peao(tabul, Cor.WHITE, this));
		colocarNovaPeca('b', 2, new peao(tabul, Cor.WHITE, this));
		colocarNovaPeca('c', 2, new peao(tabul, Cor.WHITE, this));
		colocarNovaPeca('d', 2, new peao(tabul, Cor.WHITE, this));
		colocarNovaPeca('e', 2, new peao(tabul, Cor.WHITE, this));
		colocarNovaPeca('f', 2, new peao(tabul, Cor.WHITE, this));
		colocarNovaPeca('g', 2, new peao(tabul, Cor.WHITE, this));
		colocarNovaPeca('h', 2, new peao(tabul, Cor.WHITE, this));

		colocarNovaPeca('a', 8, new Torre(tabul, Cor.BLACK));
		colocarNovaPeca('b', 8, new Cavalo(tabul, Cor.BLACK));
		colocarNovaPeca('c', 8, new bispo(tabul, Cor.BLACK));
		colocarNovaPeca('d', 8, new Rainha(tabul, Cor.BLACK));
		colocarNovaPeca('e', 8, new Rei(tabul, Cor.BLACK, this));
		colocarNovaPeca('f', 8, new bispo(tabul, Cor.BLACK));
		colocarNovaPeca('g', 8, new Cavalo(tabul, Cor.BLACK));
		colocarNovaPeca('h', 8, new Torre(tabul, Cor.BLACK));
		colocarNovaPeca('a', 7, new peao(tabul, Cor.BLACK, this));
		colocarNovaPeca('b', 7, new peao(tabul, Cor.BLACK, this));
		colocarNovaPeca('c', 7, new peao(tabul, Cor.BLACK, this));
		colocarNovaPeca('d', 7, new peao(tabul, Cor.BLACK, this));
		colocarNovaPeca('e', 7, new peao(tabul, Cor.BLACK, this));
		colocarNovaPeca('f', 7, new peao(tabul, Cor.BLACK, this));
		colocarNovaPeca('g', 7, new peao(tabul, Cor.BLACK, this));
		colocarNovaPeca('h', 7, new peao(tabul, Cor.BLACK, this));
	}

}
