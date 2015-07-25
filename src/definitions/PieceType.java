package definitions;

public enum PieceType {
	Pawn(1), Knight(3), Bishop(3), Rook(5), Queen(9), King(10);
	
	private int score;
	
	private PieceType(int score) {
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
}
