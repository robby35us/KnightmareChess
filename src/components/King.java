package components;

import java.util.ArrayList;

import definitions.Color;
import definitions.KingObserver;
import definitions.KingSubject;
import definitions.PieceObserver;
import definitions.PieceType;

public class King extends Piece implements KingSubject, KingObserver {

	private ArrayList<PieceObserver> opposingObservers;
	
	// there could possibly be multiple types that count as kings
	public King(PieceType type, Color color) {
		super(type, color);
		opposingObservers = new ArrayList<PieceObserver>();
	}

	@Override
	public boolean updateKing(Piece piece) {
		return notifyOpposingPieceObservers(this.space);
	}

	@Override
	public void registerOpposingPieceObserver(PieceObserver o) {
		opposingObservers.add(o);
	}

	@Override
	public void removeOpposingPieceObserver(PieceObserver o) {
		int index = opposingObservers.indexOf(o);
		if(index >= 0)
			opposingObservers.remove(index);
	}

	@Override
	public boolean notifyOpposingPieceObservers(Space dest) {
		space.changePiece(null);
		boolean validMove = true;
		for(PieceObserver o : opposingObservers){
			if(!o.updateOpposingPiece(dest)){
				validMove = false;
				break;
			}
		}
		space.changePiece(this);
		return validMove;
	}
}
