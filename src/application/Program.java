package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.PartidaChess;
import chess.PecaChess;
import chess.PosicaoChess;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaChess partidaChess = new PartidaChess();
		List<PecaChess> capturados = new ArrayList<>();

		while (!partidaChess.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.imprimePartida(partidaChess, capturados);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoChess fonte = UI.lerposicaoChess(sc);

				boolean[][] proxMovimento = partidaChess.proxMovimento(fonte);
				UI.clearScreen();
				UI.printBoard(partidaChess.getPecas(), proxMovimento);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoChess alvo = UI.lerposicaoChess(sc);

				PecaChess capturada = partidaChess.fazerNovoMovimento(fonte, alvo);
				if (capturada != null) {
					capturados.add(capturada);
				}
				
				if (partidaChess.getPromocao() != null) {
					System.out.print("Digite a peça para promoção(B/C/T/Q):");
					String type = sc.nextLine();
					partidaChess.trocarPecaPromovida(type);
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.imprimePartida(partidaChess, capturados);

	}

}
