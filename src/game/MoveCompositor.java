package game;
import constraints.MoveConstraint;
import moves.ActualMove;
import moves.Move;
import utility.ErrorMessage;

/*
 * This class is used to composite two moves by wrapping one around the other.
 * lastMove is the Move to be wrapped and nextMove is the wrapping ActualMove. Before 
 * it does that, however, it verifies that all the constraints for the combination 
 * are met, using the list provided by the Piece at lastMove.getInitialSpace() for 
 * the nextMove.
 */
public class MoveCompositor {
	
	/*
	 * lastMove is the previous move to wrap, nextMove is the wrapping move, and message is
	 * ErrorMessage object used for reporting any errors. Returns the composited move, or null
	 * if the constraints are not met.
	 */
	public static ActualMove compositeMoves(Move lastMove, ActualMove nextMove, ErrorMessage message){
		MoveConstraint[] constraints = lastMove.getInitialSpace().getPiece().getConstraints(nextMove.getMoveType());
	
		// verify that the move is a legal movement type for this piece
		if(constraints == null){
			message.setIllegalPattern();
			return null;
		}
		
		// for each constraint....
		for(MoveConstraint c : constraints)
			// verify that the constraint is met
			if(!c.meetsConstraint(lastMove, nextMove)){
				message.setConstraintNotMet();
				return null;
			}
		
		// returns the nextMove wrapped around the lastMove
		return nextMove.setLastMove(lastMove);
	}
}
