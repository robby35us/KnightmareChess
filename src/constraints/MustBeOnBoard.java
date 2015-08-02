package constraints;

import components.Board;
import moves.ActualMove;
import moves.Move;

/*
 * Verifies that the move will not move the piece off the board.
 */
public class MustBeOnBoard implements MoveConstraint {

	private Board board;
	
	public MustBeOnBoard(Board board){
		this.board = board;
	}
	
	@Override
	/*
	 * The meetsConstraint method looks at the lastMove object, which is often
	 * a "non-move" , such as Touch, but can be an ActualMove object, and checks 
	 * the next proposed ActualMove object to see if the proposed move meets the 
	 * conditions for this constraint. Space is the location of the piece that 
	 * is moving, or rather, the initial space of the proposed move.
	 */
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove) {
		try{
			board.getNextSpace(lastMove.getRankOffset() + nextMove.getRankOffset(), 
												 lastMove.getFileOffset() + nextMove.getFileOffset(), lastMove.getInitialSpace());
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

}
