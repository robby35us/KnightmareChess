package components;

import java.util.ArrayList;

import definitions.Color;
import definitions.KingObserver;
import definitions.KingSubject;
import definitions.PieceObserver;
import definitions.PieceType;

public class King extends Piece implements KingSubject, KingObserver {

	private ArrayList<PieceObserver> observers;
	
	// there could possibly be multiple types that count as kings
	public King(PieceType type, Color color) {
		super(type, color);
		observers = new ArrayList<PieceObserver>();
	}

	@Override
	public boolean updateKing(Piece moving) {
			return notifyPieceObservers(this.space);
	}

	@Override
	public void registerPieceObserver(PieceObserver o) {
		observers.add(o);
	}

	@Override
	public void removePieceObserver(PieceObserver o) {
		int index = observers.indexOf(o);
		if(index >= 0)
			observers.remove(index);
	}

	@Override
	public boolean notifyPieceObservers(Space dest) {
		space.changePiece(null);
		boolean validMove = true;
		for(PieceObserver o : observers){
			if(!o.updatePiece(dest)){
				validMove = false;
				break;
			}
		}
		space.changePiece(this);
		return validMove;
	}

}
