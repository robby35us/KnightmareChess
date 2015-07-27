package definitions;

public interface PieceSubject {
	public void registerKingObserver(KingObserver o);
	public void removeKingObserver(KingObserver o);
	
	// returns true if no piece flaged this move as 
	// causing check to the king
	public boolean notifyKingObservers();
}
