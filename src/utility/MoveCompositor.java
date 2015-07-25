package utility;
import game.Operations;
import components.Space;
import constraints.MoveConstraint;

import moveDecorators.ActualMove;
import moves.Move;


public class MoveCompositor {
	private static ActualMove prevMove;
	
	private MoveCompositor(){
		prevMove = null;
	}
	
	public static ActualMove compositeMoves(Move lastMove, ActualMove nextMove, Space initial, MoveConstraint[] constraints, Operations ops){
		if(constraints == null){
			System.out.println("This piece can't move that way.");
			ops.invalidMoveText();
			return null;
		}
		for(MoveConstraint c : constraints)
			if(!c.meetsConstraint(lastMove, nextMove, initial)){
					System.out.println("This piece can't move there.");
					ops.invalidMoveText();
					return null;
				}
		prevMove = nextMove;
		return nextMove.setLastMove(lastMove);
	}
	
	public static ActualMove getPreviousMove(){
		return prevMove;
	}
}
