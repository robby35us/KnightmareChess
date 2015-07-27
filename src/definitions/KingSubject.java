package definitions;

import components.Space;

public interface KingSubject {
	public void registerPieceObserver(PieceObserver o);
	public void removePieceObserver(PieceObserver o);
	
	// returns true if no piece flaged this move as 
	// causing check to the king
	public boolean notifyPieceObservers(Space dest);
}
