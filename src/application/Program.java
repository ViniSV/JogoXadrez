package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.PartidaChess;
import chess.PecaChess;
import chess.PosicaoChess;


public class Program {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		PartidaChess partidaChess = new PartidaChess();
		
		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(partidaChess.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoChess fonte = UI.lerposicaoChess(sc); 
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoChess alvo = UI.lerposicaoChess(sc);
				
				PecaChess capturada = partidaChess.fazerNovoMovimento(fonte, alvo);
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
		
	}

}
