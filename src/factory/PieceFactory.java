package factory;
import components.*;
import constraints.*;
import definitions.*;
import game.GameState;

/*
 * Used to create a piece of a given PieceType. Unlike the MoveFactory, 
 * there must be an instance of the class to use this class. This
 * is because of the need of a copy of the Board and Operations objects 
 * for this game for some of the constraints.
 */
public class PieceFactory {
	
	// Constraints for this PieceFactory
	private SingleMove singleMove;
	private MustCapture mustCapture;
	private MustMoveAlike mustMoveAlike;
	private MustBeenMoved mustBeenMoved;
	private NotBeenMoved notBeenMoved;
	//private DoubleMove doubleMove;
	private CannotCapture cannotCapture;
	private CanKingCastle canKingCastle;
	private CanQueenCastle canQueenCasltle;
	private CannotPassPiece cannotPassPiece;
	private NoPieceBehind noPieceBehind;
	private CanCaptureEnPassantLeft canCaptureEnPassantLeft;
	private CanCaptureEnPassantRight canCaptureEnPassnatRight;
		
	/*
	 * Takes a copy of the Board state the GameState object and uses them to 
	 * initialize the various constraints. 
	 */
	public PieceFactory(Board board, GameState gs){
		singleMove = new SingleMove();
		mustCapture = new MustCapture(board);
		mustMoveAlike = new MustMoveAlike();
		mustBeenMoved = new MustBeenMoved();
		notBeenMoved = new NotBeenMoved();
		//doubleMove = new DoubleMove();
		cannotCapture = new CannotCapture(board);
		canKingCastle = new CanKingCastle();
		canQueenCasltle = new CanQueenCastle();
		cannotPassPiece = new CannotPassPiece();
		noPieceBehind = new NoPieceBehind(board);
		canCaptureEnPassantLeft = new CanCaptureEnPassantLeft(gs);
		canCaptureEnPassnatRight = new CanCaptureEnPassantRight(gs);
	}
	
	/*
	 * The workhorse of the PieceFactoy, this method takes a PieceType
	 * and a Color and produces the appropriate piece with all of the
	 * appropriate MoveTypes and Constraints.
	 */
	public Piece makePiece(PieceType type, Color color){
		Piece newPiece;
		if(type == PieceType.King) // NOTE: there could possibly be multiple types that count as kings
			newPiece = new King(type, color);
		else
			newPiece = new Piece(type, color);
		switch(type){
			case Pawn : newPiece.addMove(MoveType.Forward, new MoveConstraint[]{singleMove, mustBeenMoved, cannotCapture}); 
						newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{singleMove, mustCapture});
						newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{singleMove, mustCapture});
						newPiece.addMove(MoveType.ForwardTwo, new MoveConstraint[]{singleMove, notBeenMoved, cannotCapture, noPieceBehind});
						newPiece.addMove(MoveType.EnPassantRight, new MoveConstraint[]{singleMove, canCaptureEnPassnatRight});
						newPiece.addMove(MoveType.EnPassantLeft, new MoveConstraint[]{singleMove, canCaptureEnPassantLeft});
				break;
			case Knight : newPiece.addMove(MoveType.LForwardLeft, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LForwardRight, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LRightForward, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LRightBackward, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LBackwardRight, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LBackwardLeft, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LLeftBackward, new MoveConstraint[]{singleMove});
						  newPiece.addMove(MoveType.LLeftForward, new MoveConstraint[]{singleMove});
				break;
			case Bishop : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
						  newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
						  newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
						  newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
				break;
			case Rook : newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.Right, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.Backward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.Left, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			newPiece.addMove(MoveType.ReverseKingSideCastle, new MoveConstraint[]{singleMove, notBeenMoved});
			  			newPiece.addMove(MoveType.ReverseQueenSideCastle, new MoveConstraint[]{singleMove, notBeenMoved});
				break;
			case Queen : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Right, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Backward, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
			  			 newPiece.addMove(MoveType.Left, new MoveConstraint[]{mustMoveAlike, cannotPassPiece});
				break;
			case King : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Forward, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Right, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Backward, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.Left, new MoveConstraint[]{singleMove});
 			 			newPiece.addMove(MoveType.KingSideCastle, new MoveConstraint[]{notBeenMoved, singleMove, canKingCastle});
 			 			newPiece.addMove(MoveType.QueenSideCastle, new MoveConstraint[]{notBeenMoved, singleMove, canQueenCasltle});
				break;
		}
		return newPiece;
	}
}
