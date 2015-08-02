package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveLBackwardRight.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces backwards
 * and one space to the right.
 */
public class MoveLBackwardRight extends ActualMove{
	public MoveLBackwardRight(Color color){
		super(MoveType.LBackwardRight, 
			  color);
	}
}
