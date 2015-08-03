package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveReverseQueenSideCastle.java
 * This is a Move decorator that represents a queen-side
 * rook moving three spaces towards and passed its king.
 */
public class MoveReverseQueenSideCastle extends ActualMove{
	public MoveReverseQueenSideCastle(Color color){
		super(MoveType.ReverseQueenSideCastle, 
			  color);
	}
}