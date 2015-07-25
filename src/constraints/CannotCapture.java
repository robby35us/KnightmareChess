package constraints;
import components.Board;
import components.Piece;
import components.Space;

import moveDecorators.ActualMove;
import moves.Move;


public class CannotCapture implements MoveConstraint {
	private Board board;
	
	public CannotCapture(Board board){
		this.board = board;
	}
	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initialSpace) {
		Space nextSpace = board.getNextSpace(lastMove.getRankOffset() + nextMove.getRankOffset(), 
                lastMove.getFileOffset() + nextMove.getFileOffset(), initialSpace);
		Piece defender = nextSpace.getPiece();
		return defender == null;
	}

}
