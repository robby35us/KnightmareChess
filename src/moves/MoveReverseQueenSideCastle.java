package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveReverseQueenSideCastle.java
 * This is a Move decorator that represents a piece
 * (usually a rook) moving three spaces towards the 
 * king.
 */
public class MoveReverseQueenSideCastle extends ActualMove{
	public MoveReverseQueenSideCastle(Color color){
		super(MoveType.ReverseQueenSideCastle, 
			  color);
	}
}