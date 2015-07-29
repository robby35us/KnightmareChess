package constraints;
import components.Space;

import moves.ActualMove;
import moves.Move;


public interface MoveConstraint {
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial);
}
