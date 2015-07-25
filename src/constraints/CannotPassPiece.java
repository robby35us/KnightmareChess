package constraints;

import moveDecorators.ActualMove;
import moves.Move;

import components.Space;

public class CannotPassPiece implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		return lastMove.getDestinationSpace().getPiece() == null ||
		       lastMove.getDestinationSpace().getPiece() == initial.getPiece();
	}

}
