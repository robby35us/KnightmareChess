package utility;
import game.Operations;
import components.Space;
import constraints.MoveConstraint;

import moves.ActualMove;
import moves.Move;


public class MoveCompositor {
	private static ActualMove prevMove;
	
	private MoveCompositor(){
		prevMove = null;
	}
	
	public static ActualMove compositeMoves(Move lastMove, ActualMove nextMove, Space initial, MoveConstraint[] constraints, Operations ops, 
			                                ErrorMessage message){
		if(constraints == null){
			message.setIllegalPattern();
			return null;
		}
		for(MoveConstraint c : constraints)
			if(!c.meetsConstraint(lastMove, nextMove, initial)){
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
