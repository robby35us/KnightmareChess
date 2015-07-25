package constraints;

import components.Board;
import moveDecorators.ActualMove;
import moves.Move;

import components.Space;
import definitions.Color;

public class NoPieceBehind implements MoveConstraint {
	private Board board;
	
	public NoPieceBehind(Board board){
		this.board = board;
	}

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		Space destination = board.getNextSpace(lastMove.getRankOffset() + nextMove.getRankOffset(), 
				                          lastMove.getFileOffset() + nextMove.getFileOffset(), 
				                          initial);
		Space behind = initial.getPiece().getColor() == Color.White ? destination.getSpaceBackward()
				                                                    : destination.getSpaceForward();
		return !behind.hasPiece();
	}

}
