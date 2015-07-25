package moveDecorators;
import definitions.Color;
import definitions.MoveType;

/* MoveReverseQueenSideCastle.java
 * This is a Move decorator that represents a piece
 * (usually a rook) moving three spaces towards the 
 * king.
 */
public class MoveReverseQueenSideCastle extends ActualMove{
	public MoveReverseQueenSideCastle(Color color){
		super(MoveType.ReverseQueenSideCastle.getRankOffset(), 
		      color == Color.White ? 
					  MoveType.ReverseQueenSideCastle.getFileOffset() : 
					  -MoveType.ReverseQueenSideCastle.getFileOffset(), 
			  color);
	}
}