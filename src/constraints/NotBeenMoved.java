package constraints;
import components.Space;

import moves.ActualMove;
import moves.Move;


public class NotBeenMoved implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		return !initial.getPiece().beenMoved();
	}

}
