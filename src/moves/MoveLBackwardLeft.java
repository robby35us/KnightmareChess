package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveLBackwardLeft.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces backwards
 * and one space to the left.
 */
public class MoveLBackwardLeft extends ActualMove{
	public MoveLBackwardLeft(Color color){
		super(MoveType.LBackwardLeft, 
			  color);
	}
}
