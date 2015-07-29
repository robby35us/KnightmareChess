package constraints;
import components.Space;

import moves.ActualMove;
import moves.Move;

// This constraint does not currently guarantee that
// two moves were made. Changes need to be made to 
// ensure that the class works as advertised.
public class DoubleMove implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		boolean isFirstMove = lastMove.getClass().getSuperclass() == Move.class;
		if(isFirstMove)
			return true;
		else {
			ActualMove lastActual = (ActualMove) lastMove;
			if(lastActual.getLastMove().getClass().getSuperclass() == Move.class)
				return true;
			else
				return false;
		}
	}
}
