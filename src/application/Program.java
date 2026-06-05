package application;

import chess.PartidaChess;


public class Program {

	public static void main(String[] args) {
		
		PartidaChess partidaChess = new PartidaChess();
		UI.printBoard(partidaChess.getPecas());
		
	}

}
