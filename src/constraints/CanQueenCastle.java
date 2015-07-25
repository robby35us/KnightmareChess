package constraints;
import components.Space;

import definitions.PieceType;
import moveDecorators.ActualMove;
import moves.Move;


public class CanQueenCastle implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		Space oneToLeft = initial.getSpaceLeft();
		Space twoToLeft = oneToLeft.getSpaceLeft();
		Space threeToLeft = twoToLeft.getSpaceLeft();
		Space fourToLeft = threeToLeft.getSpaceLeft();
		return oneToLeft.getPiece() == null && 
			   twoToLeft.getPiece() == null &&
			   threeToLeft.getPiece() == null &&
			   fourToLeft.getPiece() != null &&
			   fourToLeft.getPiece().getType() == PieceType.Rook &&
			   !fourToLeft.getPiece().beenMoved();
	}
}
