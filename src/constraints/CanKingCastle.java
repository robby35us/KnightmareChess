package constraints;
import components.Space;

import definitions.PieceType;
import moveDecorators.ActualMove;
import moves.Move;


public class CanKingCastle implements MoveConstraint{
	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		Space oneToRight = initial.getSpaceRight();
		Space twoToRight = oneToRight.getSpaceRight();
		Space threeToRight = twoToRight.getSpaceRight();
		return oneToRight.getPiece() == null && 
			   twoToRight.getPiece() == null &&
			   threeToRight.getPiece() != null &&
			   threeToRight.getPiece().getType() == PieceType.Rook &&
			   !threeToRight.getPiece().beenMoved();
	}
	
}
