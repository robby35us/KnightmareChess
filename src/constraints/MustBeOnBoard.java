package constraints;
import components.Board;
import components.Space;

import moves.ActualMove;
import moves.Move;


public class MustBeOnBoard implements MoveConstraint {

	private Board board;
	
	public MustBeOnBoard(Board board){
		this.board = board;
	}
	
	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initialSpace) {
		try{
			board.getNextSpace(lastMove.getRankOffset() + nextMove.getRankOffset(), 
												 lastMove.getFileOffset() + nextMove.getFileOffset(), initialSpace);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

}
