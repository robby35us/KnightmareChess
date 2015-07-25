package constraints;
import components.Space;

import moveDecorators.ActualMove;
import moves.Move;


public class MustBeenMoved implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		boolean isFirstMove = lastMove.getClass().getSuperclass() == Move.class;
		return isFirstMove || initial.getPiece().beenMoved();
	}

}
