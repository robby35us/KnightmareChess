package moves;

import definitions.Color;
import definitions.MoveType;

/* MoveActual.java
 * This class is a "abstract" class that extends
 * the Move base class for the decorator pattern 
 * classes that make up the object representation 
 * of a single move in nightmare chess. It is the 
 * base class for all decorators, which each represent
 * a portion of a move, or one "space" in a given 
 * direction.
 * 
 * The ActualMove constructor is also a converter for 
 * which way a player is looking at the board. Every
 * ActualMove decorator sends values to represent the offset
 * of the board as if it was the white player(Player1). Then, the 
 * ActualMove constructor negates the coordinates if
 * it is actually the black player(Player2).
 */

public abstract class ActualMove extends Move{
	private Move lastMove;
	private MoveType moveType;
	
	ActualMove(MoveType moveType, Color color){
		super(color == Color.White ? moveType.getRankOffset() : -moveType.getRankOffset(), 
		      color == Color.White ? moveType.getFileOffset() : -moveType.getFileOffset(),
		      null);
		this.moveType = moveType;
		if(color == Color.Black && 
		   (moveType == MoveType.KingSideCastle || moveType == MoveType.ReverseKingSideCastle ||
		    moveType == MoveType.QueenSideCastle || moveType == MoveType.ReverseQueenSideCastle)){
			rankOffset = -rankOffset;
			fileOffset = -fileOffset;
		}
	}
    
	public ActualMove setLastMove(Move lastMove){
		this.lastMove = lastMove;
		rankOffset += lastMove.getRankOffset();
		fileOffset += lastMove.getFileOffset();
		initialSpace = lastMove.getInitialSpace();
		setDestination();
		return this;
	}
	
	public boolean isLastMoveSet(){
		return lastMove == null ? false : true;
	}
	
	public Move getLastMove(){
		return lastMove;
	}
	
	public int getRankOffset(){
		return rankOffset;
	}
	
	public int getFileOffset(){
		return fileOffset;
	}

	public MoveType getMoveType() {
		return moveType;
	}


}
