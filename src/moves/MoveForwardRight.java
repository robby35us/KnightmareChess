package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveForwardRight.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both Forwards and to 
 * the right, from it's player's point of view.
 */
public class MoveForwardRight extends ActualMove{
	public MoveForwardRight(Color color){
		super(MoveType.ForwardRight, 
			  color);
	}
}
