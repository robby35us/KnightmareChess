package constraints;
import components.Space;

import moves.ActualMove;
import moves.Move;


public class MustMoveAlike implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		if(lastMove.getClass().getSuperclass().equals(Move.class))
			return true;
		else
			return lastMove.getClass().equals(nextMove.getClass());
	}

}
