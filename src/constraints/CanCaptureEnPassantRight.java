package constraints;

import utility.MoveCompositor;
import moves.ActualMove;
import moves.Move;
import moves.MoveEnPassantRight;
import moves.MoveForwardTwo;

import components.Piece;
import components.Space;
import definitions.Color;
import definitions.PieceType;
import definitions.Rank;

public class CanCaptureEnPassantRight implements MoveConstraint {

	@Override
	public boolean meetsConstraint(Move lastMove, ActualMove nextMove, Space initial) {
		Piece moving = initial.getPiece();
		if(moving.getType() != PieceType.Pawn)
			return false;
		Color color = moving.getColor();
		if((color == Color.White && initial.getRank() != Rank.Five) || 
		   (color == Color.Black && initial.getRank() != Rank.Four))
			return false;
		if(nextMove.getClass() != MoveEnPassantRight.class){
			return false;
		}
		Move prevMove = MoveCompositor.getPreviousMove();
		Space toRight = prevMove.getDestinationSpace();
		if((color == Color.White && toRight != initial.getSpaceRight()) ||
		   (color == Color.Black && toRight != initial.getSpaceLeft())) 
			return false;
		Piece toCapture = toRight.getPiece();
		if(toCapture.getType() != PieceType.Pawn || prevMove.getClass() != MoveForwardTwo.class)
			return false;
		return true;
	}

}
