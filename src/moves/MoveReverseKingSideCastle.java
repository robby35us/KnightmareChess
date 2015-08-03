package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveReverseKingSideCastle.java
 * This is a Move decorator that represents a king-side
 * rook moving two spaces toward and passing its king.
 */
public class MoveReverseKingSideCastle extends ActualMove{
	public MoveReverseKingSideCastle(Color color){
		super(MoveType.ReverseKingSideCastle, 
			  color);
	}
}