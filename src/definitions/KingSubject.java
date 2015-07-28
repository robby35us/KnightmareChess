package definitions;

import components.Space;

public interface KingSubject {
	public void registerOpposingPieceObserver(PieceObserver o);
	public void removeOpposingPieceObserver(PieceObserver o);
	
	// returns true if no piece flaged this move as 
	// causing check to the king
	public boolean notifyOpposingPieceObservers(Space dest);
}
