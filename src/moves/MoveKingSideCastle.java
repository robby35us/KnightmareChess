package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveKingSideCastle.java
 * This is a Move decorator that represents a piece
 * moving two spaces toward its king side rook.
 */
public class MoveKingSideCastle extends ActualMove{
	public MoveKingSideCastle(Color color){
		super(MoveType.KingSideCastle, 
		      color);
	}
}
