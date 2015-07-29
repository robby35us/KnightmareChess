package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveForwardRight.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both Forwards and to 
 * the right.
 */
public class MoveForwardRight extends ActualMove{
	public MoveForwardRight(Color color){
		super(MoveType.ForwardRight.getRankOffset(),
			  MoveType.ForwardRight.getFileOffset(), 
			  color);
	}
}
