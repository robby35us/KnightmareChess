package definitions;
import components.Space;

public interface PieceObserver {
	
	public boolean updateOpposingPiece(Space destination);
}
