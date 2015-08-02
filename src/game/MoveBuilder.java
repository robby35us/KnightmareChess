package game;
import moves.*;
import utility.ErrorMessage;
import components.*;
import definitions.*;
import factory.MoveFactory;

public class MoveBuilder{
	private MoveBuilder(){}
	
	public static ActualMove buildMoveObject(Space space, MoveType moveType, Operations ops, ErrorMessage message) {
		Color color = space.getPiece().getColor();
		int rankOffset = moveType.getRankOffset();
		int fileOffset = moveType.getFileOffset();
		if(moveType != MoveType.KingSideCastle && moveType != MoveType.ReverseKingSideCastle &&
		   moveType != MoveType.QueenSideCastle && moveType != MoveType.ReverseQueenSideCastle &&
		   color == Color.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		Space destination = getDestination(space, rankOffset, fileOffset);
		if(destination != null)
			return buildMoveObject(space, destination, ops, message);
		else
			return null;
	}
	
	private static Space getDestination(Space space, int rankOffset, int fileOffset) {
		while(space != null && (rankOffset != 0 || fileOffset != 0)){
			if(rankOffset > 0){
				space = space.getSpaceForward();
				rankOffset--;
			}
			else if(rankOffset < 0){
				space = space.getSpaceBackward();
				rankOffset++;
			}
			else if(fileOffset > 0){
				space = space.getSpaceRight();
				fileOffset--;
			}
			else if(fileOffset < 0){
				space = space.getSpaceLeft();
				fileOffset++;
			}
		}
		return space;
	}

	// returns null if a valid move cannot be created
	public static ActualMove buildMoveObject(Space initial, Space destination, Operations ops, ErrorMessage message){
		Piece pieceToMove = initial.getPiece();
		if(pieceToMove == null){
			message.setNoPieceToMove();
			return null;
		}
		Move move = new Touch(initial);
		return (ActualMove) buildMoveObject(move,destination,ops,message);
	}

	public static ActualMove buildMoveObject(Move move, Space destination, Operations ops, ErrorMessage message){
		Space current = move.getDestinationSpace();
		Rank rank = current.getRank();
		File file = current.getFile();
		
		while(move != null && (rank != destination.getRank() || file != destination.getFile())){
			move = selectMove(move, destination, ops, message);
			if(move == null)
				return null;
			rank = move.getDestinationSpace().getRank();
			file = move.getDestinationSpace().getFile();
		}
		return (ActualMove) move;
	}
	
	public static ActualMove selectMove(Move move, Space destination, Operations ops, ErrorMessage message){
		Rank rank = move.getDestinationSpace().getRank();
		File file = move.getDestinationSpace().getFile();
		Rank destinationRank = destination.getRank();
		File destinationFile = destination.getFile();
		if(rank == destinationRank)
			move = moveAlongRank(move, destination, ops, message);
		else if(file == destinationFile)
			move = moveAlongFile(move, destination, message);
		else if(Math.abs(rank.ordinal() - destinationRank.ordinal()) == 
			    Math.abs(file.ordinal() - destinationFile.ordinal()))
			move = moveAlongDiagonal(move, destination, message);
		else
			move = moveAlongLShape(move, destination, message);
		return (ActualMove) move;
	}
		
	private static Move moveAlongLShape(Move move, Space destination, ErrorMessage message) {
		int rankOffset = destination.getRank().ordinal() - move.getDestinationSpace().getRank().ordinal();
		int fileOffset = destination.getFile().ordinal() - move.getDestinationSpace().getFile().ordinal();
		Color pieceColor = move.getInitialSpace().getPiece().getColor();
		if(pieceColor == Color.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		if(rankOffset == 2)
			if(fileOffset == 1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LForwardRight, pieceColor), message);
			else if(fileOffset == -1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LForwardLeft, pieceColor), message);
			else 
				return null;
		if(rankOffset == -2)
			if(fileOffset == 1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LBackwardRight, pieceColor), message);
			else if(fileOffset == -1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LBackwardLeft, pieceColor), message);
			else 
				return null;
		if(fileOffset == 2)
			if(rankOffset == 1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LRightForward, pieceColor), message);
			else if(rankOffset == -1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LRightBackward, pieceColor), message);
			else 
				return null;
		if(fileOffset == -2)
			if(rankOffset == 1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LLeftForward, pieceColor), message);
			else if(rankOffset == -1)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.LLeftBackward, pieceColor), message);
			else 
				return null;
		return null;
	}

	private static Move moveAlongDiagonal(Move move, Space destination, ErrorMessage message) {
		int rankOffset = destination.getRank().ordinal() - move.getDestinationSpace().getRank().ordinal();
		int fileOffset = destination.getFile().ordinal() - move.getDestinationSpace().getFile().ordinal();
		Color pieceColor = move.getInitialSpace().getPiece().getColor();
		if(pieceColor == Color.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		if(rankOffset > 0)
			if(fileOffset > 0)
				if(move.getInitialSpace().getPiece().getType() == PieceType.Pawn && destination.getPiece() == null)
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.EnPassantRight, pieceColor), message);
				else
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ForwardRight, pieceColor), message);
			else // fileOffset < 0
				if(move.getInitialSpace().getPiece().getType() == PieceType.Pawn && destination.getPiece() == null)
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.EnPassantLeft, pieceColor), message);
				else
					return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ForwardLeft, pieceColor), message);
		else // rankOffset < 0
			if(fileOffset > 0)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.BackwardRight, pieceColor), message);
			else // fileOffset < 0
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.BackwardLeft, pieceColor), message);
	}

	private static Move moveAlongFile(Move move, Space destination, ErrorMessage message) {
		int rankOffset = destination.getRank().ordinal() - move.getDestinationSpace().getRank().ordinal();
		Color pieceColor = move.getInitialSpace().getPiece().getColor();
		if(pieceColor == Color.Black)
			rankOffset = -rankOffset;
		if(move.getInitialSpace().getPiece().getType() == PieceType.Pawn && rankOffset == 2)
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ForwardTwo, pieceColor), message);
		if(rankOffset > 0)
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Forward, pieceColor), message);
		else // rankOffset < 0
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Backward, pieceColor), message);
	}

	private static Move moveAlongRank(Move move, Space destination, Operations ops, ErrorMessage message) {
		int fileOffset = destination.getFile().ordinal() - move.getDestinationSpace().getFile().ordinal();
		Space initial = move.getInitialSpace();
		Color pieceColor = initial.getPiece().getColor();
		if(initial.getPiece().getType() == PieceType.King && Math.abs(fileOffset) == 2){
			if(fileOffset == 2){
				ActualMove result = MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.KingSideCastle, pieceColor), message);
				if(result != null){
					Space rookDest = initial.getSpaceRight();
					Space rookInit = destination.getSpaceRight();
					ActualMove rookMove = buildMoveObject(rookInit, rookDest, ops, new ErrorMessage());
					ops.makeMove(rookMove, pieceColor == Color.White ? Turn.Player1 : Turn.Player2, message);
				}
				return result; 
			}
			else if(fileOffset == -2){
				ActualMove result =MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.QueenSideCastle, pieceColor), message);
				if(result != null){
					Space rookDest = initial.getSpaceLeft();
					Space rookInit = rookDest.getSpaceLeft().getSpaceLeft().getSpaceLeft();
					ActualMove rookMove = buildMoveObject(rookInit, rookDest, ops, new ErrorMessage());
					ops.makeMove(rookMove, pieceColor == Color.White ? Turn.Player1 : Turn.Player2, message);
				}
				return result; 
			}
			else
				return null;
		}
		else if(initial.getPiece().getType() == PieceType.Rook && 
			    MoveCompositor.getPreviousMove().getClass() == MoveKingSideCastle.class){
			if(fileOffset == -2)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ReverseKingSideCastle, pieceColor), message);
			else{
				return null;
			}
		}
		else if(initial.getPiece().getType() == PieceType.Rook &&
				MoveCompositor.getPreviousMove().getClass() == MoveQueenSideCastle.class){
			if(fileOffset == 3)
				return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.ReverseQueenSideCastle, pieceColor), message);
			else
				return null;
		}
		if(pieceColor == Color.Black)
			fileOffset = -fileOffset;
		if(fileOffset > 0)
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Right, pieceColor), message);
		else // fileOffset < 0
			return MoveCompositor.compositeMoves(move, MoveFactory.makeMoveObject(MoveType.Left, pieceColor), message);
	}

	public static ActualMove buildMoveObject(ActualMove move, MoveType moveType, Operations ops, ErrorMessage message) {
		Space dest = getDestination(move.getDestinationSpace(), moveType.getRankOffset(), moveType.getFileOffset());
		if(dest == null)
			return null;
		return buildMoveObject(move, dest, ops, message);
	}
}
