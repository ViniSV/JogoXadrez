package boardgame;

public abstract class Peca {

	protected Posicao posi;
	private Tabuleiro tab;
	public Peca(Tabuleiro tab) {
		this.tab = tab;
		posi = null;
	}
	
	protected Tabuleiro getTab() {
		return tab;
	}
	
	public abstract boolean[][] movimentoSPossiveis();
	
	public boolean movimentOPossivel(Posicao posi) {
		return movimentoSPossiveis()[posi.getLinha()][posi.getColuna()];
	}
	
	public boolean temAlgumMovimento() {
		boolean[][] mat = movimentoSPossiveis();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
