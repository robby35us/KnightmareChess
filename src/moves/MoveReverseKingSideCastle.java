package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveReverseKingSideCastle.java
 * This is a Move decorator that represents a piece
 * (usually a rook) moving two spaces toward its king.
 */
public class MoveReverseKingSideCastle extends ActualMove{
	public MoveReverseKingSideCastle(Color color){
		super(MoveType.ReverseKingSideCastle, 
			  color);
	}
}