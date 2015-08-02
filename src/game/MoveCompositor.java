package game;
import constraints.MoveConstraint;
import moves.ActualMove;
import moves.Move;
import utility.ErrorMessage;


public class MoveCompositor {
	private static ActualMove prevMove;
	
	private MoveCompositor(){
		prevMove = null;
	}
	
	public static ActualMove compositeMoves(Move lastMove, ActualMove nextMove, ErrorMessage message){
		MoveConstraint[] constraints = lastMove.getInitialSpace().getPiece().getConstraints(nextMove.getMoveType());
		if(constraints == null){
			message.setIllegalPattern();
			return null;
		}
		for(MoveConstraint c : constraints)
			if(!c.meetsConstraint(lastMove, nextMove)){
				message.setConstraintNotMet();
				return null;
			}
		return nextMove.setLastMove(lastMove);
	}
	
	public static ActualMove getPreviousMove(){
		return prevMove;
	}
	
	public static void setPreviousMove(ActualMove prevMove){
		MoveCompositor.prevMove = prevMove;
	}
}
