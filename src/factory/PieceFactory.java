package factory;
import components.*;
import constraints.*;
import definitions.*;


public class PieceFactory {
	private MustBeOnBoard mbob;
	private SingleMove sm;
	private MustCapture mc;
	private MustMoveAlike mma;
	private MustBeenMoved mbm;
	private NotBeenMoved nbm;
	//private DoubleMove dm;
	private CannotCapture cc;
	private CanKingCastle ckc;
	private CanQueenCastle cqc;
	private CannotPassPiece cpp;
	private NoPieceBehind npb;
	private CanCaptureEnPassantLeft ccepl;
	private CanCaptureEnPassantRight ccepr;
		
	public PieceFactory(Board board){
		mbob = new MustBeOnBoard(board);
		sm = new SingleMove();
		mc = new MustCapture(board);
		mma = new MustMoveAlike();
		mbm = new MustBeenMoved();
		nbm = new NotBeenMoved();
		//dm = new DoubleMove();
		cc = new CannotCapture(board);
		ckc = new CanKingCastle();
		cqc = new CanQueenCastle();
		cpp = new CannotPassPiece();
		npb = new NoPieceBehind(board);
		ccepl = new CanCaptureEnPassantLeft();
		ccepr = new CanCaptureEnPassantRight();
	}
	
	public Piece makePiece(PieceType type, Color color){
		Piece newPiece;
		if(type == PieceType.King) // there could possibly be multiple types that count as kings
			newPiece = new King(type, color);
		else
			newPiece = new Piece(type, color);
		switch(type){
			case Pawn : newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mbob,sm, mbm, cc}); 
						newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mbob,sm,mc});
						newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mbob,sm,mc});
						newPiece.addMove(MoveType.ForwardTwo, new MoveConstraint[]{mbob, sm, nbm, cc, npb});
						newPiece.addMove(MoveType.EnPassantRight, new MoveConstraint[]{mbob, sm, ccepr});
						newPiece.addMove(MoveType.EnPassantLeft, new MoveConstraint[]{mbob, sm, ccepl});
				break;
			case Knight : newPiece.addMove(MoveType.LForwardLeft, new MoveConstraint[]{mbob,sm});
						  newPiece.addMove(MoveType.LForwardRight, new MoveConstraint[]{mbob,sm});
						  newPiece.addMove(MoveType.LRightForward, new MoveConstraint[]{mbob,sm});
						  newPiece.addMove(MoveType.LRightBackward, new MoveConstraint[]{mbob,sm});
						  newPiece.addMove(MoveType.LBackwardRight, new MoveConstraint[]{mbob,sm});
						  newPiece.addMove(MoveType.LBackwardLeft, new MoveConstraint[]{mbob,sm});
						  newPiece.addMove(MoveType.LLeftBackward, new MoveConstraint[]{mbob,sm});
						  newPiece.addMove(MoveType.LLeftForward, new MoveConstraint[]{mbob,sm});
				break;
			case Bishop : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mbob,mma, cpp});
						  newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mbob,mma, cpp});
						  newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{mbob,mma, cpp});
						  newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{mbob,mma, cpp});
				break;
			case Rook : newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mbob,mma, cpp});
			  			newPiece.addMove(MoveType.Right, new MoveConstraint[]{mbob,mma, cpp});
			  			newPiece.addMove(MoveType.Backward, new MoveConstraint[]{mbob,mma, cpp});
			  			newPiece.addMove(MoveType.Left, new MoveConstraint[]{mbob,mma, cpp});
			  			newPiece.addMove(MoveType.ReverseKingSideCastle, new MoveConstraint[]{mbob, sm, nbm});
			  			newPiece.addMove(MoveType.ReverseQueenSideCastle, new MoveConstraint[]{mbob, sm, nbm});
				break;
			case Queen : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mbob,mma, cpp});
			  			 newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mbob,mma, cpp});
			  			 newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{mbob,mma, cpp});
			  			 newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{mbob,mma, cpp});
			  			 newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mbob,mma, cpp});
			  			 newPiece.addMove(MoveType.Right, new MoveConstraint[]{mbob,mma, cpp});
			  			 newPiece.addMove(MoveType.Backward, new MoveConstraint[]{mbob,mma, cpp});
			  			 newPiece.addMove(MoveType.Left, new MoveConstraint[]{mbob,mma, cpp});
				break;
			case King : newPiece.addMove(MoveType.ForwardLeft, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.ForwardRight, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.BackwardRight, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.BackwardLeft, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.Forward, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.Right, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.Backward, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.Left, new MoveConstraint[]{mbob,sm});
 			 			newPiece.addMove(MoveType.KingSideCastle, new MoveConstraint[]{nbm,sm,ckc});
 			 			newPiece.addMove(MoveType.QueenSideCastle, new MoveConstraint[]{nbm,sm,cqc});
				break;
		}
		return newPiece;
	}
}
