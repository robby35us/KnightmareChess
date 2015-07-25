package moveDecorators;
import definitions.Color;
import definitions.MoveType;

/* MoveQueenSideCastle.java
 * This is a Move decorator that represents a piece
 * moving two spaces toward its queen's rook.
 */
public class MoveQueenSideCastle extends ActualMove{
	public MoveQueenSideCastle(Color color){
		super(MoveType.QueenSideCastle.getRankOffset(), 
		      color == Color.White ?
		    		  MoveType.QueenSideCastle.getFileOffset() : 
		    		  -MoveType.QueenSideCastle.getFileOffset(), 
		      color);
	}
}
