package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveQueenSideCastle.java
 * This is a Move decorator that represents a piece
 * moving two spaces toward its queen's rook.
 */
public class MoveQueenSideCastle extends ActualMove{
	public MoveQueenSideCastle(Color color){
		super(MoveType.QueenSideCastle, 
		      color);
	}
}
