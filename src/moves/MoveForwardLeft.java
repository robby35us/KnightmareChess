package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveForwardLeft.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both forward and to 
 * the left.
 */
public class MoveForwardLeft extends ActualMove{
	public MoveForwardLeft(Color color){
		super(MoveType.ForwardLeft, 
		      color);
	}
}
