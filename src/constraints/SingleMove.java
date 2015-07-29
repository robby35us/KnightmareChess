package constraints;
import components.Space;

import moves.ActualMove;
import moves.Move;


public class SingleMove implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		return lastMove.getClass().getSuperclass().equals(Move.class);
	}
}
