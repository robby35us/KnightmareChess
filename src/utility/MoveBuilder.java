package utility;
import game.Operations;
import moveDecorators.*;
import moves.*;
import components.*;
import definitions.*;

public class MoveBuilder{
	private MoveBuilder(){}
	
	
	// returns null if a valid move cannot be created
	public static ActualMove buildMoveObject(Space initial, Space destination, Operations ops){
		Piece pieceToMove = initial.getPiece();
		if(pieceToMove == null)
			return null;
		Color pieceColor = pieceToMove.getColor();
		Rank rank = initial.getRank();
		File file = initial.getFile();
		Rank destinationRank = destination.getRank();
		File destinationFile = destination.getFile();
		Move move = new Touch(initial);
		RankAndFile currentRankAndFile = computeRankAndFile(move, initial);
		
		while(move != null && (rank != destinationRank || file != destinationFile)){
			if(rank == destinationRank)
				move = moveAlongRank(move, currentRankAndFile, initial, destination, pieceColor, ops);
			else if(file == destinationFile)
				move = moveAlongFile(move, currentRankAndFile, initial, destination, pieceColor, ops);
			else if(Math.abs(rank.ordinal() - destinationRank.ordinal()) == 
				    Math.abs(file.ordinal() - destinationFile.ordinal()))
				move = moveAlongDiagonal(move, currentRankAndFile, initial, destination, pieceColor, ops);
			else
				move = moveAlongLShape(move, currentRankAndFile, initial, destination, pieceColor, ops);
			if(move == null){
				return null;
			}
			currentRankAndFile = computeRankAndFile(move, initial);
			rank = currentRankAndFile.getRank();
			file = currentRankAndFile.getFile();
		}
		return (ActualMove) move;
	}

	private static RankAndFile computeRankAndFile(Move move, Space initial){
			return new RankAndFile(Rank.values()[initial.getRank().ordinal() + move.getRankOffset()],
							   	   File.values()[initial.getFile().ordinal() + move.getFileOffset()]);	
	}
		
	private static Move moveAlongLShape(Move move, RankAndFile currentRankAndFile, Space initial, Space destination, Color pieceColor, Operations ops) {
		int rankOffset = destination.getRank().ordinal() - currentRankAndFile.getRank().ordinal();
		int fileOffset = destination.getFile().ordinal() - currentRankAndFile.getFile().ordinal();
		if(pieceColor == Color.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		if(rankOffset == 2)
			if(fileOffset == 1)
				return MoveCompositor.compositeMoves(move, new MoveLForwardRight(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LForwardRight), ops);
			else if(fileOffset == -1)
				return MoveCompositor.compositeMoves(move, new MoveLForwardLeft(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LForwardLeft), ops);
			else 
				return null;
		if(rankOffset == -2)
			if(fileOffset == 1)
				return MoveCompositor.compositeMoves(move, new MoveLBackwardRight(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LBackwardRight), ops);
			else if(fileOffset == -1)
				return MoveCompositor.compositeMoves(move, new MoveLBackwardLeft(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LBackwardLeft), ops);
			else 
				return null;
		if(fileOffset == 2)
			if(rankOffset == 1)
				return MoveCompositor.compositeMoves(move, new MoveLRightForward(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LRightForward), ops);
			else if(rankOffset == -1)
				return MoveCompositor.compositeMoves(move, new MoveLRightBackward(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LRightBackward), ops);
			else 
				return null;
		if(fileOffset == -2)
			if(rankOffset == 1)
				return MoveCompositor.compositeMoves(move, new MoveLLeftForward(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LLeftForward), ops);
			else if(rankOffset == -1)
				return MoveCompositor.compositeMoves(move, new MoveLLeftBackward(pieceColor), initial, initial.getPiece().getConstraints(MoveType.LLeftBackward), ops);
			else 
				return null;
		return null;
	}

	private static Move moveAlongDiagonal(Move move, RankAndFile currentRankAndFile, Space initial, Space destination, Color pieceColor, Operations ops) {
		int rankOffset = destination.getRank().ordinal() - currentRankAndFile.getRank().ordinal();
		int fileOffset = destination.getFile().ordinal() - currentRankAndFile.getFile().ordinal();
		if(pieceColor == Color.Black){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
		if(rankOffset > 0)
			if(fileOffset > 0)
				return MoveCompositor.compositeMoves(move, new MoveForwardRight(pieceColor), initial, initial.getPiece().getConstraints(MoveType.ForwardRight), ops);
			else // fileOffset < 0
				return MoveCompositor.compositeMoves(move, new MoveForwardLeft(pieceColor), initial, initial.getPiece().getConstraints(MoveType.ForwardLeft), ops);
		else // rankOffset < 0
			if(fileOffset > 0)
				return MoveCompositor.compositeMoves(move, new MoveBackwardRight(pieceColor), initial, initial.getPiece().getConstraints(MoveType.BackwardRight), ops);
			else // fileOffset < 0
				return MoveCompositor.compositeMoves(move, new MoveBackwardLeft(pieceColor), initial, initial.getPiece().getConstraints(MoveType.BackwardLeft), ops);
	}

	private static Move moveAlongFile(Move move, RankAndFile currentRankAndFile, Space initial, Space destination, Color pieceColor, Operations ops) {
		int rankOffset = destination.getRank().ordinal() - currentRankAndFile.getRank().ordinal();
		if(pieceColor == Color.Black)
			rankOffset = -rankOffset;
		if(initial.getPiece().getType() == PieceType.Pawn && rankOffset == 2)
			return MoveCompositor.compositeMoves(move, new MoveForwardTwo(pieceColor), initial, initial.getPiece().getConstraints(MoveType.ForwardTwo), ops);
		if(rankOffset > 0)
			return MoveCompositor.compositeMoves(move, new MoveForward(pieceColor), initial, initial.getPiece().getConstraints(MoveType.Forward), ops);
		else // rankOffset < 0
			return MoveCompositor.compositeMoves(move, new MoveBackward(pieceColor), initial, initial.getPiece().getConstraints(MoveType.Backward), ops);
	}

	private static Move moveAlongRank(Move move, RankAndFile currentRankAndFile, Space initial, Space destination, Color pieceColor, Operations ops) {
		int fileOffset = destination.getFile().ordinal() - currentRankAndFile.getFile().ordinal();
		if(pieceColor == Color.Black)
			fileOffset = -fileOffset;
		if(initial.getPiece().getType() == PieceType.King && Math.abs(fileOffset) == 2){
			if(fileOffset == 2){
				ActualMove result = MoveCompositor.compositeMoves(move, new MoveKingSideCastle(pieceColor), initial, initial.getPiece().getConstraints(MoveType.KingSideCastle), ops);
				if(result != null){
					Space rookDest = initial.getSpaceRight();
					Space rookInit = destination.getSpaceRight();
					ActualMove rookMove = buildMoveObject(rookInit, rookDest, ops);
					Operations.makeMove(rookMove, pieceColor == Color.White ? Turn.Player1 : Turn.Player2, ops);
				}
				return result; 
			}
			else if(fileOffset == -2){
				ActualMove result =MoveCompositor.compositeMoves(move, new MoveQueenSideCastle(pieceColor), initial, initial.getPiece().getConstraints(MoveType.QueenSideCastle), ops);
				if(result != null){
					Space rookDest = initial.getSpaceLeft();
					Space rookInit = rookDest.getSpaceLeft().getSpaceLeft().getSpaceLeft();
					ActualMove rookMove = buildMoveObject(rookInit, rookDest, ops);
					Operations.makeMove(rookMove, pieceColor == Color.White ? Turn.Player1 : Turn.Player2, ops);
				}
				return result; 
			}
			else
				return null;
		}
		else if(initial.getPiece().getType() == PieceType.Rook && 
			    MoveCompositor.getPreviousMove().getClass() == MoveKingSideCastle.class){
			if(fileOffset == -2)
				return MoveCompositor.compositeMoves(move, new MoveReverseKingSideCastle(pieceColor), initial, initial.getPiece().getConstraints(MoveType.ReverseKingSideCastle), ops);
			else{
				return null;
			}
		}
		else if(initial.getPiece().getType() == PieceType.Rook &&
				MoveCompositor.getPreviousMove().getClass() == MoveQueenSideCastle.class){
			if(fileOffset == 3)
				return MoveCompositor.compositeMoves(move, new MoveReverseQueenSideCastle(pieceColor), initial, initial.getPiece().getConstraints(MoveType.ReverseQueenSideCastle), ops);
			else
				return null;
		}
		else if(fileOffset > 0)
			return MoveCompositor.compositeMoves(move, new MoveRight(pieceColor), initial, initial.getPiece().getConstraints(MoveType.Right), ops);
		else // fileOffset < 0
			return MoveCompositor.compositeMoves(move, new MoveLeft(pieceColor), initial, initial.getPiece().getConstraints(MoveType.Left), ops);
	}
}
